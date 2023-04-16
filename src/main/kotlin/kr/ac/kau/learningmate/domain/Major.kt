package kr.ac.kau.learningmate.domain

import AbstractAuditEntity
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Major (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(nullable = false, length = 255)
    var majorName: String,

    @Column(nullable = false, length = 255)
    var description: String,


    ):AbstractAuditEntity()