package kr.ac.kau.learningmate.repository

import kr.ac.kau.learningmate.domain.Topic
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
interface TopicRepository : JpaRepository<Topic, Long> {
    fun findAllBySubjectId(subjectId: Long, pageable: Pageable): Page<Topic>
}
