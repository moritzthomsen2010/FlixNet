package com.gerontology.flixnet.repository

import com.gerontology.flixnet.model.Movie
import com.gerontology.flixnet.model.State
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository : JpaRepository<Movie, String> {
    @Query("""
        SELECT m FROM Movie m
        WHERE (:state IS NULL OR m.state = :state)
          AND (:genre IS NULL OR m.genre = :genre)
          AND (:minRuntimeMinutes IS NULL OR m.lengthInMinutes >= :minRuntimeMinutes)
          AND (:maxRuntimeMinutes IS NULL OR m.lengthInMinutes <= :maxRuntimeMinutes)
    """)
    fun findAllWithFilter(
        @Param("state") state: State?,
        @Param("genre") genre: String?,
        @Param("minRuntimeMinutes") minRuntimeMinutes: Int?,
        @Param("maxRuntimeMinutes") maxRuntimeMinutes: Int?,
    ): List<Movie>

    @Query("SELECT m FROM Movie m WHERE m.state = 'AVAILABLE'")
    fun findAllAvailable(): List<Movie>

    @Modifying
    @Query("UPDATE Movie m SET m.state = :state WHERE m.id IN :ids")
    fun updateMovieStates(
        @Param("ids") ids: List<String>,
        @Param("state") state: State,
    )
}
