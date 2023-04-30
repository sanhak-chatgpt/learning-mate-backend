package kr.ac.kau.learningmate.controller.dto

sealed class WhisperDto {

    class Request(
        val model: String,
        val file: ByteArray,
    )

    data class Response(
        val text: String,
    )
}
