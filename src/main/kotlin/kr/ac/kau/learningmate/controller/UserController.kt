package kr.ac.kau.learningmate.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import kr.ac.kau.learningmate.controller.dto.LectureDto
import kr.ac.kau.learningmate.controller.dto.UserDto
import kr.ac.kau.learningmate.service.JwtService
import kr.ac.kau.learningmate.service.LectureService
import kr.ac.kau.learningmate.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService,
    private val jwtService: JwtService,
    private val lectureService: LectureService
) {

    @PostMapping("/token/issue")
    fun issueToken(): UserDto.Me {
        return userService.createUser()
    }

    @GetMapping("/lectures")
    @Operation(summary = "자신의 lecture 전부 조회 api", security = [SecurityRequirement(name = "bearer-key")])
    fun getLecturesByUserId(): ResponseEntity<List<LectureDto.Response>> {
        val userId = jwtService.getUserId()
        val lectures = lectureService.getUserLectures(userId)
        return ResponseEntity.ok(lectures)
    }

    @PutMapping("/name")
    @Operation(summary = "nickName 변경 api", security = [SecurityRequirement(name = "bearer-key")])
    fun updateUserNickname(@RequestBody name: UserDto.NickName): ResponseEntity<UserDto.NickName> {
        val userId = jwtService.getUserId()
        userService.updateUserNickname(userId, name)
        return ResponseEntity.ok(name)
    }
}
