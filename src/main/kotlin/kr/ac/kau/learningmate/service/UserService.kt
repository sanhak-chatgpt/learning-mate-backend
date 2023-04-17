package kr.ac.kau.learningmate.service

import kr.ac.kau.learningmate.domain.User
import kr.ac.kau.learningmate.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    fun findById(id: Long): User {
        return User(
            id = 1,
            name = "구영민",
        )
        return userRepository.findByIdOrNull(id)!!
    }
}
