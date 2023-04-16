package kr.ac.kau.learningmate.domain

import AbstractAuditEntity
import java.time.LocalDateTime
import javax.persistence.*


@Entity
class Lecture (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User,

    @ManyToOne
    @JoinColumn(name = "topic_id")
    var topic: Topic,

    @Column(nullable = false, columnDefinition = "TEXT")
    var audioUrl: String,

    @Column(nullable = false, columnDefinition = "TEXT")
    var transcribed: String,

    @Column(nullable = false)
    var score: Integer,

    @Column(nullable = false, columnDefinition = "TEXT")
    var strength: String,

    @Column(nullable = false, columnDefinition = "TEXT")
    var weakness: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: Status,

    @Column(nullable = false)
    var helpfulnessRating: Integer,

) :AbstractAuditEntity() {
    enum class Status{
        IN_PROGRESS, SUCCESS, FAILURE
    }
}