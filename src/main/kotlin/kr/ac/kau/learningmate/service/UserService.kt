package kr.ac.kau.learningmate.service

import kr.ac.kau.learningmate.controller.dto.UserDto
import kr.ac.kau.learningmate.domain.User
import kr.ac.kau.learningmate.repository.UserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class UserService(
    private val userRepository: UserRepository,
    private val jwtService: JwtService,
    @Value("\${jwt.secret}") private val jwtSecret: String,
    @Value("\${jwt.issuer}") private val jwtIssuer: String

) {

    fun findById(id: Long): User {
        return userRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("없는 사용자 입니다.")
    }

    fun createUser(): UserDto.Me {
        val lastUserId = userRepository.findFirstByOrderByIdDesc()?.id ?: 0
        val name = "사용자${lastUserId + 1}"

        val jwt = jwtService.generateJwt(lastUserId + 1)

        val user = User(
            id = 0L,
            name = name,
        )

        val savedUser = userRepository.save(user)

        return UserDto.Me(
            name = savedUser.name,
            authToken = jwt,
        )
    }

    fun updateUserNickname(userId: Long, nickname: UserDto.NickName) {
        val user = userRepository.findById(userId)
            .orElseThrow { EntityNotFoundException("User not found with id: $userId") }
        user.name = nickname.name // Assuming the nickname is accessible via the `nickname` property
        userRepository.save(user)
    }
}
