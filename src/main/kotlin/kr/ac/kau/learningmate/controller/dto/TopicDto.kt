package kr.ac.kau.learningmate.controller.dto

sealed class TopicDto {
    data class Response(
        val id: Long,
        val topicName: String,
        val description: String
    )
}
