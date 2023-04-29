package kr.ac.kau.learningmate.repository

import kr.ac.kau.learningmate.domain.Subject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface SubjectRepository : JpaRepository<Subject, Long> {

    fun findAllByMajorId(majorId: Long, pageable: Pageable): Page<Subject>
}
