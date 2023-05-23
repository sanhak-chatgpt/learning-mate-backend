package kr.ac.kau.learningmate.service

import kr.ac.kau.learningmate.controller.dto.RankingDto
import kr.ac.kau.learningmate.repository.LectureRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RankingService(private val lectureRepository: LectureRepository) {

    fun calculateRanking(userId: Long): Pair<RankingDto.UserScore?, String> {
        val thirtyDaysAgo = LocalDateTime.now().minusDays(30)

        val lectures = lectureRepository.findByCreatedAtAfter(thirtyDaysAgo)

        if (lectures.isEmpty()) {
            return Pair(null, "최근 30일 간 강의를 진행하지 않았어요. 강의를 하러 진행해볼까요?")
        }

        val rankings = lectures
            .groupingBy { it.user.id }
            .eachCount()
            .map { RankingDto.UserScore(it.key, it.value.toDouble(), 0) }
            .sortedByDescending { it.score }
            .mapIndexed { index, userRanking -> userRanking.copy(rank = (index + 1).toLong()) }

        val userRanking = rankings.find { it.userId == userId }

        if (userRanking != null) {
            val percentile = userRanking.rank.toDouble() / rankings.size * 100
            val message = when {
                percentile <= 30 -> "대단해요! AI 평가 점수가 상위 ${percentile.toInt()}%인 강의 고수시군요."
                percentile <= 70 -> "AI 평가 점수가 상위 ${percentile.toInt()}%에요."
                else -> "이런, 조금 더 분발하는 게 어때요? AI 평가 점수가 상위 ${percentile.toInt()}%이네요."
            }
            return Pair(userRanking, message)
        } else {
            return Pair(null, "최근에 강의를 진행한 적이 없군요. 강의하러 가볼까요?")
        }
    }
}
