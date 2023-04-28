package kr.ac.kau.learningmate.service

import kr.ac.kau.learningmate.domain.User
import kr.ac.kau.learningmate.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    fun findById(id: Long): User {
        return userRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("없는 사용자 입니다.")
    }
}
