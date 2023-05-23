package kr.ac.kau.learningmate.controller.dto

sealed class MajorDto {

    data class Response(
        val id: Long,
        val majorName: String,
        val description: String
    )

    data class AdminRequest(
        val majorName: String,
        val description: String
    )
}
