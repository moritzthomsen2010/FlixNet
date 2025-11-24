package com.gerontology.flixnet.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
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
    val id: Long = 0,

    @Column(name = "title")
    val title: String = "",

    @Column(name = "genre")
    val genre: String = "",

    @Column(name = "rental_status")
    val rentalStatus: String = ""
)
