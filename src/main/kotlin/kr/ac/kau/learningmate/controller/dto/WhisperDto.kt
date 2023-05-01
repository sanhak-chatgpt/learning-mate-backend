package kr.ac.kau.learningmate.controller.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

sealed class WhisperDto {

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    class Request(
        val model: String,
        val file: ByteArray,
    )

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Response(
        val text: String,
    )
}
