package kr.ac.kau.learningmate.domain

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

enum class status{
    IN_PROGRESS, SUCCESS, FAILURE
}
@Entity
class lecture (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(nullable = false)
    var user_id: Long,

    @Column(nullable = false)
    var topic_id: Long,

    @Column(nullable = false, length = 255)
    var audio_url: String,

    @Column(nullable = false)
    var transcribed: String,

    @Column(nullable = false)
    var score: Integer,

    @Column(nullable = false, length = 255)
    var strength: String,

    @Column(nullable = false)
    var weakness: String,

    @Column(nullable = false)
    var status: status,

    @Column(nullable = false, length = 255)
    var helpfulness_rating: Integer,

    @Column(nullable = false)
    var createdAt: LocalDateTime,

    @Column(nullable = false)
    var updatedAt: LocalDateTime,
)