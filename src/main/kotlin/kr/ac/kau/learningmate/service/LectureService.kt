package kr.ac.kau.learningmate.service

import kr.ac.kau.learningmate.controller.dto.LectureDto
import kr.ac.kau.learningmate.domain.Lecture
import kr.ac.kau.learningmate.repository.LectureRepository
import kr.ac.kau.learningmate.repository.TopicRepository
import kr.ac.kau.learningmate.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException
import javax.transaction.Transactional

@Service
class LectureService(
    private val lectureRepository: LectureRepository,
    private val topicRepository: TopicRepository,
    private val userRepository: UserRepository,
    private val gptService: GptService,
    // private val s3Client: AmazonS3
) {

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
        // TODO: S3 파일을 내려받기
        // TODO: Whisper로 API 호출하기
        // TODO: GPT API 호출하기
        // TODO: Lecture 저장하기
    }

    fun rateHelpfulness(id: Long, userId: Long, helpfulness: LectureDto.RateHelpfulness) {
        val lecture = lectureRepository.findByIdOrNull(id = id)
            ?: throw EntityNotFoundException("Lecture not found with id : $id")

        lecture.helpfulnessRating = helpfulness.helpfulnessRating

        lectureRepository.save(lecture)
    }
}
