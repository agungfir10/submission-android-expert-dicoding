package com.agungfir.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agungfir.core.ui.MovieAdapter
import com.agungfir.favorite.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoriteViewModel.favoriteMovies.observe(this) {
            movieAdapter = MovieAdapter()
            movieAdapter.setData(it)
            binding.rvMovies.adapter = movieAdapter
        }
    }
}