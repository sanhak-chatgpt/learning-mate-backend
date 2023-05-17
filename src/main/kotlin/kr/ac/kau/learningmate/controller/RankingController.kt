package kr.ac.kau.learningmate.controller

import kr.ac.kau.learningmate.service.RankingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/ranking")
class RankingController(private val rankingService: RankingService) {

    @GetMapping("/{userId}")
    fun getRanking(@PathVariable userId: Long): ResponseEntity<Map<String, Any>> {
        val (ranking, message) = rankingService.calculateRanking(userId)
        return if (ranking != null) {
            ResponseEntity.ok(mapOf("ranking" to ranking, "message" to message))
        } else {
            ResponseEntity.ok(mapOf("message" to message))
        }
    }
}
