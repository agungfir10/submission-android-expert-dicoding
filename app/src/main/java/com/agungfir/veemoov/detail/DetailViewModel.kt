package com.agungfir.veemoov.detail

import androidx.lifecycle.ViewModel
import com.agungfir.core.domain.model.Movie
import com.agungfir.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newState: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newState)
}