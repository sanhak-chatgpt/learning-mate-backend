package kr.ac.kau.learningmate.repository

import kr.ac.kau.learningmate.domain.Lecture
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface LectureRepository : JpaRepository<Lecture, Long> {
    fun findByCreatedAtAfter(date: LocalDateTime): List<Lecture>

    fun findByUserId(userId: Long): List<Lecture>

    @Query("SELECT l FROM Lecture l ORDER BY l.createdAt DESC")
    fun findRecentLectures(): List<Lecture>
}
