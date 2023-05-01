package kr.ac.kau.learningmate.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.GetObjectRequest
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kr.ac.kau.learningmate.controller.dto.GptDto
import kr.ac.kau.learningmate.controller.dto.LectureDto
import kr.ac.kau.learningmate.controller.dto.WhisperDto
import kr.ac.kau.learningmate.domain.Lecture
import kr.ac.kau.learningmate.repository.LectureRepository
import kr.ac.kau.learningmate.repository.TopicRepository
import kr.ac.kau.learningmate.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.net.URI
import javax.persistence.EntityNotFoundException
import javax.transaction.Transactional

@Service
class LectureService(
    private val lectureRepository: LectureRepository,
    private val topicRepository: TopicRepository,
    private val userRepository: UserRepository,
    private val whisperService: WhisperService,
    private val gptService: GptService,
    private val s3Client: AmazonS3,
    private val objectMapper: ObjectMapper,
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun getLecture(id: Long, userId: Long): LectureDto.Response {

        val lecture = lectureRepository.findByIdOrNull(id)
            ?: throw EntityNotFoundException("Lecture not found with id: $id")

        return LectureDto.Response(
            id = lecture.id,
            majorId = lecture.topic.subject.major.id,
            majorName = lecture.topic.subject.major.majorName,
            subjectId = lecture.topic.subject.id,
            subjectName = lecture.topic.subject.subjectName,
            topicId = lecture.topic.id,
            topicName = lecture.topic.topicName,
            transcribed = lecture.transcribed,
            score = lecture.score,
            strength = lecture.strength,
            weakness = lecture.weakness,
            status = lecture.status,
            helpfulnessRating = lecture.helpfulnessRating
        )
    }

    @Transactional
    fun createLecture(request: LectureDto.Request, userId: Long): Lecture {
        val topic = topicRepository.findByIdOrNull(request.topicId)
            ?: throw EntityNotFoundException("Topic not found with id ${request.topicId}")

        val user = userRepository.findByIdOrNull(userId)
            ?: throw EntityNotFoundException("User not found with id $userId")

        val lecture = Lecture(
            id = 0L,
            user = user,
            topic = topic,
            audioUrl = request.audioUrl,
            transcribed = null,
            score = null,
            strength = null,
            weakness = null,
            status = Lecture.Status.IN_PROGRESS,
            helpfulnessRating = null,
        )

        return lectureRepository.save(lecture)
    }

    @Async
    fun updateLectureAsync(lecture: Lecture) {

        log.info("Called updateLectureAsync - id = ${lecture.id}")

        try {
            // S3 버킷에 저장된 녹음 파일 키
            val audioUrl = lecture.audioUrl

            // S3에서 녹음 파일 내려받기
            val s3Object = s3Client.getObject(
                GetObjectRequest("learning-mate-content-prod", URI(audioUrl).path.substring(1))
            )
            val audioBytes = s3Object.objectContent.readAllBytes()

            // Whisper API 호출하여 TTS script 생성하기
            val model = "whisper-1" // Whisper 모델명
            val request = WhisperDto.Request(model, audioBytes)
            val script = whisperService.transcribeAudio(request)

            val prompt = """
            아래의 강의를 0~100사이의 점수에 대해 측정하고, 이 강의의 내용에서만 좋았던 점과 개선할 점을 여러개 분석해줘. 아래의 형식으로 데이터를 JSON 으로 만들어줘. 설명하지마. 마크다운으로 주지 마. 오로지 JSON 형태로만 줘. 
            {"score":"/* 0~100사이의 강의에 대한 점수에 대해 매우 야박하고 까다롭게 측정 */","strength":"/* 200자 내외의 좋았던 점 설명 */","weakness":"/* 200자 내외의고쳐야 할 점 설명*/"}
            
            $script
            """.trimIndent()

            // GPT API 호출하여 script를 기반으로 Lecture 생성하기
            val generatedText = gptService.completeChat(prompt)
            if (generatedText.isNotEmpty()) {
                // parsing 성공하는 것까지

                val parsed = objectMapper.readValue<GptDto.LectureResponseDto>(generatedText)
                lecture.score = parsed.score
                lecture.strength = parsed.strength
                lecture.weakness = parsed.weakness

                lecture.status = Lecture.Status.SUCCESS
            }
            lecture.transcribed = generatedText
        } catch (e: Exception) {
            log.error("Error during updating lecture", e)
            lecture.status = Lecture.Status.FAILURE
        } finally {
            lectureRepository.save(lecture)
        }
    }

    fun rateHelpfulness(id: Long, userId: Long, helpfulness: LectureDto.RateHelpfulness) {
        val lecture = lectureRepository.findByIdOrNull(id = id)
            ?: throw EntityNotFoundException("Lecture not found with id : $id")

        lecture.helpfulnessRating = helpfulness.helpfulnessRating

        lectureRepository.save(lecture)
    }
}
