package kr.ac.kau.learningmate.controller.dto

sealed class UserResponseDto {

    data class Me(
        val name: String,
    )
}
