package kr.ac.kau.learningmate.controller


import kr.ac.kau.learningmate.controller.dto.UserResponseDto
import kr.ac.kau.learningmate.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
@RestController
class UserController(
        private val userService: UserService,
) {

    @GetMapping("/api/v1/users/me")
    fun me(): UserResponseDto.Me {
        val userId = 1L // TODO: 나중에는 JWT에서 가져오기

        return userService
            .findById(id = userId)
            .let {
                UserResponseDto.Me(
                    name = it.name,
                )
            }
    }
}
