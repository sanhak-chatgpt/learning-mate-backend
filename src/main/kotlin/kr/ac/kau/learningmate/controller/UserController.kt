package kr.ac.kau.learningmate.controller

import kr.ac.kau.learningmate.controller.dto.UserDto
import kr.ac.kau.learningmate.service.JwtService
import kr.ac.kau.learningmate.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService,
    private val jwtService: JwtService,
) {

    @GetMapping("/me")
    fun me(): UserDto.Me {
        val userId = 1L // TODO: 나중에는 JWT에서 가져오기

        return userService
            .findById(id = userId)
            .let {
                UserDto.Me(
                    name = it.name,
                )
            }
    }
    @PostMapping("/token/issue")
    fun issueToken(@RequestParam name: String): String {
        return userService.createUserByName(name)
    }
}
