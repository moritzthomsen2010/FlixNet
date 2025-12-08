package com.gerontology.flixnet.controller

import com.gerontology.flixnet.model.Movie
import com.gerontology.flixnet.service.MovieService
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.notFound
import org.springframework.http.ResponseEntity.ok
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/movies")
class MovieController(private val movieService: MovieService) {

    @GetMapping("/")
    fun getMovies(): ResponseEntity<List<Movie>> {
        return ok(movieService.findAll())
    }

    @GetMapping("/{id}")
    fun getMovie(@PathVariable("id") id: Long): ResponseEntity<Movie> {
        val foundMovie: Movie? = movieService.findById(id)

        return if (foundMovie != null) {
            ok(movieService.findById(id))
        } else {
            notFound().build<Movie>()
        }
    }

    @Transactional
    @PostMapping("/")
    fun createMovie(@RequestBody movie: Movie): ResponseEntity<Movie> {
        return ok(movieService.save(movie))
    }

    @Transactional
    @PutMapping("/")
    fun updateMovie(@RequestBody movie: Movie): ResponseEntity<Movie> {
        return ok(movieService.updateMovie(movie))
    }

    @Transactional
    @DeleteMapping("/{id}")
    fun deleteMovie(@PathVariable("id") id: Long): ResponseEntity<Movie> {
        movieService.deleteById(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
