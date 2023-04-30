package kr.ac.kau.learningmate.controller.dto

class ChatGptDto {
    data class Request(
        val prompt: String,
        val max_tokens: Int,
        val n: Int,
        val temperature: Double
    )

    data class Response(
        val choices: String
    )
}
