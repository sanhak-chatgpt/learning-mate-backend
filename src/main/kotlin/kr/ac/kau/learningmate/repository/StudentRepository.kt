package kr.ac.kau.learningmate.repository

import kr.ac.kau.learningmate.domain.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<Student, Long>
