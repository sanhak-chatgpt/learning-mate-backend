package kr.ac.kau.learningmate.controller.dto

sealed class UserDto {

    data class Me(
        val name: String,
    )
}
