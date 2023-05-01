package kr.ac.kau.learningmate.controller.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

class GptDto {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Request(
        val model: String,
        val temperature: Double,
        val messages: List<Message>
    ) {
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
        data class Message(
            val role: String,
            val content: String,
        )
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Response(
        val id: String,
        val `object`: String,
        val created: Long,
        val model: String,
        val usage: Usage,
        val choices: List<Choice>
    ) {
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
        data class Usage(
            val promptTokens: Int,
            val completionTokens: Int,
            val totalTokens: Int
        )

        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
        data class Choice(
            val message: Message,
            val finishReason: String,
            val index: Int
        ) {
            @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
            data class Message(
                val role: String,
                val content: String
            )
        }
    }

    data class LectureResponseDto(
        val score: Int,
        val strength: String,
        val weakness: String,
    )
}
