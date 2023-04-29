package kr.ac.kau.learningmate.service

import kr.ac.kau.learningmate.controller.dto.LectureDto
import kr.ac.kau.learningmate.repository.LectureRepository
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class LectureService(private val lectureRepository: LectureRepository) {

    fun getLecture(id: Long, userId: Long): LectureDto.Response {
        val lecture = lectureRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Lecture not found with id: $id") }

        return LectureDto.Response(
            id = lecture.id!!,
            majorId = lecture.topic.subject.major.id!!,
            majorName = lecture.topic.subject.major.majorName,
            subjectId = lecture.topic.subject.id!!,
            subjectName = lecture.topic.subject.subjectName,
            topicId = lecture.topic.id!!,
            topicName = lecture.topic.topicName,
            transcribed = lecture.transcribed,
            score = lecture.score,
            strength = lecture.strength,
            weakness = lecture.weakness,
            status = lecture.status,
            helpfulnessRating = lecture.helpfulnessRating
        )
    }
}
