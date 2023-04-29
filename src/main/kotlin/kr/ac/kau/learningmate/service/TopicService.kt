package kr.ac.kau.learningmate.service

import kr.ac.kau.learningmate.domain.Topic
import kr.ac.kau.learningmate.repository.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicService(private val topicRepository: TopicRepository) {
    fun getAllTopics(subjectId: Long, page: Int, size: Int): Page<Topic> {
        val pageable: Pageable = PageRequest.of(page, size)
        return topicRepository.findAllBySubjectId(subjectId, pageable)
    }
}
