package kr.ac.kau.learningmate.domain

import AbstractAuditEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Subject (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @ManyToOne
    @JoinColumn(name = "major_id")
    var major: Major,

    @Column(nullable = false, length = 255)
    var subjectName: String,

    @Column(nullable = false, length = 255)
    var description: String,

) :AbstractAuditEntity()