package kr.ac.kau.learningmate.repository

import kr.ac.kau.learningmate.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>
