package kr.ac.kau.learningmate.controller

import kr.ac.kau.learningmate.controller.dto.LectureDto
import kr.ac.kau.learningmate.service.JwtService
import kr.ac.kau.learningmate.service.LectureService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/lecture")
class LectureController(
    private val lectureService: LectureService,
    private val jwtService: JwtService,
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("")
    fun createLecture(@RequestBody request: LectureDto.Request): LectureDto.Response {
        val userId = jwtService.getUserId()
        val lecture = lectureService.createLecture(request, userId)
        log.info("Call updateLectureAsync from createLecture controller - id = ${lecture.id}")
        lectureService.updateLectureAsync(lecture)
        return lectureService.getLecture(lecture.id, userId)
    }

    @GetMapping("/{id}")
    fun getLecture(
        @PathVariable id: Long,
    ): LectureDto.Response {
        val userId = jwtService.getUserId()
        return lectureService.getLecture(id, userId)
    }

    @PostMapping("/{id}/rate-helpfulness")
    fun rateHelpfulness(
        @PathVariable id: Long,
        @RequestBody helpfulness: LectureDto.RateHelpfulness
    ) {
        val userId = jwtService.getUserId()
        lectureService.rateHelpfulness(id, userId, helpfulness)
    }
}
