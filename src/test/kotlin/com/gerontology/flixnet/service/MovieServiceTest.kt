package com.gerontology.flixnet.service

import com.gerontology.flixnet.exception.MovieNotFoundException
import com.gerontology.flixnet.model.Movie
import com.gerontology.flixnet.model.State
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import kotlin.test.assertEquals

@SpringBootTest
@ActiveProfiles("test")
class MovieServiceTest {
    @BeforeEach
    fun setUp() {
        movieService.deleteAll()
    }

    @Autowired
    private lateinit var movieService: MovieService

    @Test
    fun `should successfully save movie and return it`() {
        val movie = Movie(
            id = "1",
            title = "My Neighbor Totoro",
            genre = "Animation",
            state = State.AVAILABLE,
            lengthInMinutes = 86
        )

        val savedMovie = movieService.save(movie)

        Assertions.assertNotNull(savedMovie)
        Assertions.assertEquals(movie, savedMovie)
    }

    @Test
    fun `should not save invalid movie`() {
        val movie = Movie(
            id = "",
            title = "My Neighbor Totoro",
            genre = "",
            state = State.AVAILABLE,
            lengthInMinutes = 86
        )

        assertThrows<Exception> {
            movieService.save(movie)
        }
    }

    @Test
    fun `should find all movies`() {
        // given
        saveMovie("1")
        saveMovie("2")

        // when
        val movies = movieService.findAll()

        // then
        Assertions.assertEquals(2, movies.size)
        Assertions.assertEquals(listOf("1", "2"), movies.map { it.id })
    }

    @Test
    fun `should return movie for id`() {
        // given
        val movie = saveMovie("1")
        saveMovie("42")

        // when
        val foundMovie = movieService.findById(movie.id)

        // then
        Assertions.assertEquals(movie, foundMovie)
    }

    @Test
    fun `should throw MovieNotFoundException for invalid id`() {
        // given
        val movie = saveMovie("1")

        // when
        val exception = assertThrows<MovieNotFoundException> {
            movieService.findById("42")
        }

        // then
        Assertions.assertEquals("Movie with id 42 not found!", exception.message)
    }

    @Test
    fun `should find movie with constraints`() {
        // given
        saveMovie("1")
        saveMovie("2")
        saveMovie("3", runtime = 100)

        // when
        val movies = movieService.findAllWithFilter(
            state = State.AVAILABLE,
            genre = "animation",
            minRuntimeMinutes = 80,
            maxRuntimeMinutes = 90
        )

        // then
        Assertions.assertEquals(2, movies.size)
    }

    @Test
    fun `should find movie with some constraints for runtime`() {
        // given
        saveMovie("1")
        saveMovie("2")
        saveMovie("3", runtime = 100)

        // when
        val movies = movieService.findAllWithFilter(
            state = null,
            genre = null,
            minRuntimeMinutes = 80,
            maxRuntimeMinutes = 90
        )

        // then
        Assertions.assertEquals(2, movies.size)
        Assertions.assertEquals(listOf("1", "2"), movies.map { it.id })
    }

    @Test
    fun `should find movie with some constraints for genre`() {
        // given
        saveMovie("1")
        saveMovie("2", genre = "sci-fi")
        saveMovie("3")

        // when
        val movies = movieService.findAllWithFilter(
            state = null,
            genre = "animation",
            minRuntimeMinutes = null,
            maxRuntimeMinutes = null
        )

        // then
        Assertions.assertEquals(2, movies.size)
        Assertions.assertEquals(listOf("1", "3"), movies.map { it.id })
    }

    @Test
    fun `should find movie with some constraints for state`() {
        // given
        saveMovie("1", state = State.LENT_OUT)
        saveMovie("2")
        saveMovie("3")

        // when
        val movies = movieService.findAllWithFilter(
            state = State.AVAILABLE,
            genre = null,
            minRuntimeMinutes = null,
            maxRuntimeMinutes = null
        )

        // then
        Assertions.assertEquals(2, movies.size)
        Assertions.assertEquals(listOf("2", "3"), movies.map { it.id })
    }

    @Test
    fun `should find all available movies`() {
        // given
        saveMovie("1")
        saveMovie("2")
        saveMovie("3", state = State.LENT_OUT)

        // when
        val movies = movieService.findAvailable()

        // then
        Assertions.assertEquals(2, movies.size)
    }

    @Test
    fun `should delete movie`() {
        // given
        saveMovie("1")
        Assertions.assertEquals(1, movieService.findAll().size)

        // when
        movieService.deleteById("1")

        // then
        Assertions.assertEquals(0, movieService.findAll().size)
    }

    @Test
    fun `should update many movies`() {
        // given
        saveMovie("1")
        saveMovie("2")

        // when
        movieService.updateMovieStates(listOf("1", "2"), State.ARCHIVED)

        // then
        assertEquals(State.ARCHIVED, movieService.findById("1").state)
        assertEquals(State.ARCHIVED, movieService.findById("2").state)
    }

    @Test
    fun `should update many movies and rollback all updates if one fails`() {
        // given
        saveMovie("1")
        saveMovie("2")

        // when
        assertThrows<MovieNotFoundException> {
            movieService.updateMovieStates(listOf("1", "2", "3"), State.ARCHIVED)
        }

        // then (no update, because of rollback)
        assertEquals(State.AVAILABLE, movieService.findById("1").state)
        assertEquals(State.AVAILABLE, movieService.findById("2").state)
    }

    private fun saveMovie(
        id: String,
        runtime: Int = 86,
        genre: String = "animation",
        state: State = State.AVAILABLE
    ): Movie {
        val movie = Movie(
            id = id,
            title = "My Neighbor Totoro",
            genre = genre,
            state = state,
            lengthInMinutes = runtime
        )
        return movieService.save(movie)
    }
}
