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
class Subject(
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

) : AbstractAuditEntity()
