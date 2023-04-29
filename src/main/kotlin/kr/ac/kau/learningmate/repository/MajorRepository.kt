package kr.ac.kau.learningmate.repository

import kr.ac.kau.learningmate.domain.Major
import org.springframework.data.jpa.repository.JpaRepository

interface MajorRepository : JpaRepository<Major, Long>
