package kr.ac.kau.learningmate.domain

import AbstractAuditEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Lecture(
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

    @Column(nullable = true, columnDefinition = "TEXT")
    var transcribed: String?,

    @Column(nullable = true)
    var score: Int?,

    @Column(nullable = true, columnDefinition = "TEXT")
    var strength: String?,

    @Column(nullable = true, columnDefinition = "TEXT")
    var weakness: String?,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: Status,

    @Column(nullable = true)
    var helpfulnessRating: Int?,

) : AbstractAuditEntity() {
    enum class Status {
        IN_PROGRESS, SUCCESS, STT_EMPTY, FAILURE
    }
}
