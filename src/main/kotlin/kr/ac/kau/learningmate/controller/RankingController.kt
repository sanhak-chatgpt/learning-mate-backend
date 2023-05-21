package kr.ac.kau.learningmate.controller

import kr.ac.kau.learningmate.controller.dto.LectureRankingDto
import kr.ac.kau.learningmate.service.JwtService
import kr.ac.kau.learningmate.service.RankingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/ranking")
class RankingController(
    private val rankingService: RankingService,
    private val jwtService: JwtService
) {

    @GetMapping
    fun getRanking(): ResponseEntity<LectureRankingDto.LectureRankingDto> {
        val userId = jwtService.getUserId()
        val (ranking, message) = rankingService.calculateRanking(userId)
        val response = LectureRankingDto.LectureRankingDto(ranking, message)
        return ResponseEntity.ok(response)
    }
}
