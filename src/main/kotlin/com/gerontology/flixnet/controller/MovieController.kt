package com.gerontology.flixnet.controller

import com.gerontology.flixnet.model.Movie
import com.gerontology.flixnet.service.MovieService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/movies")
class MovieController(private val movieService: MovieService) {

    @GetMapping("/{id}")
    fun getMovie(@PathVariable("id") id: Int): ResponseEntity<Movie> {
        return ok(movieService.findById(id))
    }

    @GetMapping
    fun getAllMovies(): ResponseEntity<List<Movie>> {
        return ok(movieService.findAll())
    }
}