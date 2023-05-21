package kr.ac.kau.learningmate.controller.dto

class RankingDto {
    data class UserScore(
        val userId: Long,
        val score: Double,
        val rank: Long
    )
}
