package com.agungfir.core.data.source.local

import com.agungfir.core.data.source.local.entity.MovieEntity
import com.agungfir.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    suspend fun insertMovies(movie: List<MovieEntity>) = movieDao.insertMovies(movie)

    fun setFavoriteMovie(movie: MovieEntity, isFavoriteNewState: Boolean) {
        movie.isFavorite = isFavoriteNewState
        movieDao.updateFavoriteMovie(movie)
    }

}