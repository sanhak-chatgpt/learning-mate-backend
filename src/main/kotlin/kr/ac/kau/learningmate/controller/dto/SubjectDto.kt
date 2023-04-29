package kr.ac.kau.learningmate.controller.dto

sealed class SubjectDto {
    data class Response(
        val id: Long,
        val subjectName: String,
        val description: String
    )
}
