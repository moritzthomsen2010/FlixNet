package com.gerontology.flixnet.service

import com.gerontology.flixnet.model.Movie
import com.gerontology.flixnet.repository.MovieRepository
import org.springframework.stereotype.Service

@Service
class MovieService(private val movieRepository: MovieRepository) {

    fun findById(id: Int): Movie? {
        return movieRepository.findById(id)
    }

    fun findAll(): List<Movie> {
        return movieRepository.findAll()
    }

}