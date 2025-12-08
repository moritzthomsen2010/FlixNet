package com.gerontology.flixnet.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "movies")
data class Movie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long,

    @Column(name = "title")
    val title: String = "",

    @Column(name = "genre")
    val genre: String = "",

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    val state: State = State.AVAILABLE,

    @Column(name = "length_in_minutes")
    val lengthInMinutes: Int = 0,
)
