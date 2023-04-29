package kr.ac.kau.learningmate.repository

import kr.ac.kau.learningmate.domain.Lecture
import org.springframework.data.jpa.repository.JpaRepository

interface LectureRepository : JpaRepository<Lecture, Long>
