package kr.ac.kau.learningmate.domain

import AbstractAuditEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(nullable = false, length = 255)
    var name: String,

    ):AbstractAuditEntity()