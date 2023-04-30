package kr.ac.kau.learningmate.controller

import kr.ac.kau.learningmate.controller.dto.GeneratePresignedUrlDto
import kr.ac.kau.learningmate.service.GeneratePresignedUrlService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class GeneratePresignedUrlController(private val generatePresignedUrlService: GeneratePresignedUrlService) {

    @GetMapping("/generate-presigned-url")
    fun getPresignedUrl(): GeneratePresignedUrlDto.Response {
        val userId = 1L
        val presignedUrl = generatePresignedUrlService.generatePresignedUrl(userId = userId)
        return GeneratePresignedUrlDto.Response(presignedUrl)
    }
}
