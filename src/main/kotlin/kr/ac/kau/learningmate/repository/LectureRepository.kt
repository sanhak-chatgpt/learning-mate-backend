package kr.ac.kau.learningmate.repository

import kr.ac.kau.learningmate.domain.Lecture
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface LectureRepository : JpaRepository<Lecture, Long> {
    fun findByCreatedAtAfter(date: LocalDateTime): List<Lecture>
}
