package kr.ac.kau.learningmate.controller

import kr.ac.kau.learningmate.controller.dto.SubjectDto
import kr.ac.kau.learningmate.service.SubjectService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/subject")
class SubjectController(private val subjectService: SubjectService) {

    @GetMapping
    fun getAllSubject(
        @RequestParam majorId: Long,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): Page<SubjectDto.Response> {
        val pageable: Pageable = PageRequest.of(page, size)
        return subjectService.getSubject(majorId = majorId, pageable = pageable)
            .map {
                SubjectDto.Response(
                    id = it.id,
                    subjectName = it.subjectName,
                    description = it.description
                )
            }
    }

    @PostMapping
    fun postSubject(@RequestBody subjectDto: SubjectDto.Request): ResponseEntity<SubjectDto.Response> {
        val createdSubject = subjectService.createSubject(
            majorId = subjectDto.majorId,
            subjectName = subjectDto.subjectName,
            description = subjectDto.description
        )
        val responseDto = SubjectDto.Response(
            id = createdSubject.id,
            subjectName = createdSubject.subjectName,
            description = createdSubject.description
        )
        return ResponseEntity.ok(responseDto)
    }
}
