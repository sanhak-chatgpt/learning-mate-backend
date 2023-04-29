package kr.ac.kau.learningmate.controller.dto

class TopicController {
    data class TopicRequestDto(
        val subjectId: Long,
        val page: Int,
        val size: Int
    )

    data class TopicResponseDto(
        val content: List<TopicDto>,
        val pagination: PaginationDto
    )

    data class TopicDto(
        val id: Long,
        val topicName: String,
        val description: String
    )

    data class PaginationDto(
        val page: Int,
        val size: Int,
        val totalElements: Long,
        val totalPages: Int
    )
}
