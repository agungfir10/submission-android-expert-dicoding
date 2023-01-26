package com.agungfir.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.agungfir.core.ui.MovieAdapter
import com.agungfir.favorite.databinding.ActivityFavoriteBinding
import com.agungfir.veemoov.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.favorite_feature)

        loadKoinModules(favoriteModule)
        onClick()
        movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, it)
            startActivity(intent)
        }

        favoriteViewModel.favoriteMovies.observe(this) {
            if (it.isNotEmpty()) {
                movieAdapter.setData(it)
                binding.rvMovies.adapter = movieAdapter
                renderFavorites()
            } else {
                renderEmpty()
            }
        }
    }

    private fun onClick() {
        binding.btnFinish.setOnClickListener {
            finish()
        }
    }

    private fun renderFavorites() {
        binding.apply {
            btnFinish.visibility = View.GONE
            textView.visibility = View.GONE
            rvMovies.visibility = View.VISIBLE
        }
    }

    private fun renderEmpty() {
        binding.apply {
            btnFinish.visibility = View.VISIBLE
            textView.visibility = View.VISIBLE
            rvMovies.visibility = View.GONE
        }
    }
}