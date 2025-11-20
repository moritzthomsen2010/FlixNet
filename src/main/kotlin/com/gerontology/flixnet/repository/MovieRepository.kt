package com.gerontology.flixnet.repository

import com.gerontology.flixnet.model.Movie
import org.springframework.stereotype.Repository

@Repository
class MovieRepository {

    val movies = listOf(
        Movie(0, "The Shawshank Redemption", "Drama", "Available"),
        Movie(1, "The Godfather", "Crime", "Rented"),
        Movie(2, "The Dark Knight", "Action", "Available"),
        Movie(3, "Pulp Fiction", "Crime", "Available"),
        Movie(4, "Forrest Gump", "Drama", "Rented")
    )

    fun findAll(): List<Movie> {
        return movies
    }

    fun findById(id: Int): Movie? {
        return movies.getOrNull(id)
    }

}