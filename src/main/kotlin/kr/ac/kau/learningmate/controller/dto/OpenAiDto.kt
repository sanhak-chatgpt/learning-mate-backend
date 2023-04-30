package kr.ac.kau.learningmate.controller.dto

class OpenAiDto {
    data class Response(
        val args: Map<String, String>,
        val headers: Map<String, String>,
        val origin: String,
        val url: String,
    )
}
