package kr.ac.kau.learningmate.controller.dto

import kr.ac.kau.learningmate.domain.Lecture

sealed class LectureDto {
    data class Response(
        val id: Long,
        val majorId: Long,
        val majorName: String,
        val subjectId: Long,
        val subjectName: String,
        val topicId: Long,
        val topicName: String,
        val transcribed: String,
        val score: Integer,
        val strength: String?,
        val weakness: String?,
        val status: Lecture.Status,
        val helpfulnessRating: Integer
    )

    data class Request(
        val topicId: Long,
        val audioUrl: String,
    )
}