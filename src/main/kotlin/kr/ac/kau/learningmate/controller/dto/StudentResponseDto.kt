package kr.ac.kau.learningmate.controller.dto

sealed class StudentResponseDto {

    data class List(
        val name: String,
        val studentId: Long,
    )
}
