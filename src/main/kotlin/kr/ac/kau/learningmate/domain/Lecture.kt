package kr.ac.kau.learningmate.domain

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

enum class Status{
    IN_PROGRESS, SUCCESS, FAILURE
}
@Entity
class Lecture (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(nullable = false)
    var userId: Long,

    @Column(nullable = false)
    var topicId: String,

    @Column(nullable = false, columnDefinition = "TEXT")
    var audioUrl: String,

    @Column(nullable = false)
    var transcribed: String,

    @Column(nullable = false)
    var score: Integer,

    @Column(nullable = false, columnDefinition = "TEXT")
    var strength: String,

    @Column(nullable = false, columnDefinition = "TEXT")
    var weakness: String,

    @Column(nullable = false)
    var status: Status,

    @Column(nullable = false)
    var helpfulnessRating: Integer,

    @Column(nullable = false)
    var createdAt: LocalDateTime,

    @Column(nullable = false)
    var updatedAt: LocalDateTime,
)