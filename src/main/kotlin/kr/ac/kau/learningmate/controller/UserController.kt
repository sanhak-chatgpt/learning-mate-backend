package kr.ac.kau.learningmate.controller

import kr.ac.kau.learningmate.controller.dto.UserDto
import kr.ac.kau.learningmate.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService,
) {

    @PostMapping("/token/issue")
    fun issueToken(): UserDto.Me {
        return userService.createUser()
    }
}
