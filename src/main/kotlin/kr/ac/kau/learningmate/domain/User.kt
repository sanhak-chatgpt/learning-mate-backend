package kr.ac.kau.learningmate.domain

import AbstractAuditEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(nullable = false, length = 255)
    var name: String,

    @Column(nullable = false)
    var authToken: String

) : AbstractAuditEntity() {
    constructor(name: String, auth_token: String) : this(0L, name, auth_token)
}
