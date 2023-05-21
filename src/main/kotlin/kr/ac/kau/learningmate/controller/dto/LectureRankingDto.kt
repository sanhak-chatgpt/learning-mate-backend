package kr.ac.kau.learningmate.controller.dto

class LectureRankingDto {
    data class Response(
        val ranking: RankingDto.UserScore?,
        val message: String
    )
}
