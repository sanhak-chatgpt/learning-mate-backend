package kr.ac.kau.learningmate.service

import kr.ac.kau.learningmate.domain.Topic
import kr.ac.kau.learningmate.repository.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicService(private val topicRepository: TopicRepository) {
    fun getTopic(subjectId: Long, pageable: Pageable): Page<Topic> {
        return topicRepository.findAllBySubjectId(subjectId = subjectId, pageable = pageable)
    }
}
