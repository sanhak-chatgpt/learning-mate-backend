package kr.ac.kau.learningmate.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class topic (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(nullable = false)
    var subject_id: Long,

    @Column(nullable = false, length = 255)
    var topic_name: String,

    @Column(nullable = false, length = 255)
    var description: String,

    @Column(nullable = false)
    var createdAt: LocalDateTime,

    @Column(nullable = false)
    var updatedAt: LocalDateTime,
)