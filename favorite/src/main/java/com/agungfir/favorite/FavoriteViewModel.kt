package com.agungfir.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.agungfir.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovies = movieUseCase.getFavoriteMovies().asLiveData()
}