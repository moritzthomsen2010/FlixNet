package com.gerontology.flixnet.service

import com.gerontology.flixnet.model.Movie
import com.gerontology.flixnet.repository.MovieRepository
import org.springframework.stereotype.Service

@Service
class MovieService(private val movieRepository: MovieRepository) {

    fun findById(id: Long): Movie? {
        return movieRepository.findById(id).orElse(null)
    }

    fun findAll(): List<Movie> {
        return movieRepository.findAll()
    }

    fun save(movie: Movie): Movie {
        return movieRepository.save<Movie>(movie)
    }

    fun updateMovie(movie: Movie): Movie {
        return movieRepository.save<Movie>(movie)
    }

    fun deleteById(id: Long) {
        return movieRepository.deleteById(id)
    }

}
