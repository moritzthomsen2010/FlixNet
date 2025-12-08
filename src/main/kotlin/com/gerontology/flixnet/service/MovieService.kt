package com.gerontology.flixnet.service

import com.gerontology.flixnet.exception.MovieNotFoundException
import com.gerontology.flixnet.model.Movie
import com.gerontology.flixnet.model.State
import com.gerontology.flixnet.repository.MovieRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class MovieService(private val movieRepository: MovieRepository) {

    fun findById(id: String): Movie {
        return movieRepository.findById(id).orElseThrow {
            MovieNotFoundException("Movie with id $id not found")
        }
    }

    fun findAll(): List<Movie> {
        return movieRepository.findAll()
    }

    open fun findAvailable(): List<Movie> {
        return movieRepository.findAllAvailable()
    }

    @Transactional
    fun save(movie: Movie): Movie {
        return movieRepository.save<Movie>(movie)
    }

    @Transactional
    fun updateMovie(movie: Movie): Movie {
        return movieRepository.save<Movie>(movie)
    }

    @Transactional
    open fun updateMovieStates(ids: List<String>, state: State) {
        return movieRepository.updateMovieStates(ids, state)
    }

    @Transactional
    fun deleteById(id: String) {
        movieRepository.deleteById(id)
    }

    @Transactional
    open fun deleteAll() {
        movieRepository.deleteAll()
    }

    fun findAllWithFilter(
        state: State?,
        genre: String?,
        minRuntimeMinutes: Int?,
        maxRuntimeMinutes: Int?,
    ): List<Movie> {
        return movieRepository.findAllWithFilter(state, genre, minRuntimeMinutes, maxRuntimeMinutes)
    }

}
