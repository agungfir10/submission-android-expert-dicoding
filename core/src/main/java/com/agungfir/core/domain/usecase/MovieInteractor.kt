package com.agungfir.core.domain.usecase

import com.agungfir.core.data.Resource
import com.agungfir.core.domain.model.Movie
import com.agungfir.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val iMovieRepository: IMovieRepository) : MovieUseCase {

    override fun getAllMovies(): Flow<Resource<List<Movie>>> = iMovieRepository.getAllMovies()

    override fun getFavoriteMovies(): Flow<List<Movie>> = iMovieRepository.getFavoriteMovies()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) =
        iMovieRepository.setFavoriteMovie(movie, state)
}