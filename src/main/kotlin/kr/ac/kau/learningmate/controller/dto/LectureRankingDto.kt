package kr.ac.kau.learningmate.controller.dto

class LectureRankingDto {
    data class LectureRankingDto(
        val ranking: RankingDto.UserScore?,
        val message: String
    )
}
