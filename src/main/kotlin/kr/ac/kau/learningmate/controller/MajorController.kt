package kr.ac.kau.learningmate.controller

import kr.ac.kau.learningmate.controller.dto.MajorDto
import kr.ac.kau.learningmate.service.MajorService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/major")
class MajorController(private val majorService: MajorService) {
    @GetMapping
    fun getAllMajors(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): Page<MajorDto.Response> {
        val pageable: Pageable = PageRequest.of(page, size)
        return majorService.getAllMajors(pageable = pageable)
            .map {
                MajorDto.Response(
                    id = it.id,
                    majorName = it.majorName,
                    description = it.description
                )
            }
    }
}
