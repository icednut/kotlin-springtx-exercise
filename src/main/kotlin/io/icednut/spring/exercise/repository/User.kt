package io.icednut.spring.exercise.repository

import jakarta.persistence.*

@Entity
@Table(name = "my_user")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long? = null,

    @Column(name = "user_name")
    var name: String
) {

    constructor(name: String) : this(null, name)
}