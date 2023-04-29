package kr.ac.kau.learningmate.domain

import AbstractAuditEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Topic(
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

) : AbstractAuditEntity()
