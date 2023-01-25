package com.agungfir.veemoov.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.agungfir.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movies = movieUseCase.getAllMovies().asLiveData()
}