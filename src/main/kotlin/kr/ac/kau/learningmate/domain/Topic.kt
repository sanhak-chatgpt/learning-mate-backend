package kr.ac.kau.learningmate.domain

import AbstractAuditEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Topic (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @ManyToOne
    @JoinColumn(name = "subject_id")
    var subject: Subject,

    @Column(nullable = false, length = 255)
    var topicName: String,

    @Column(nullable = false, length = 255)
    var description: String,

):AbstractAuditEntity()