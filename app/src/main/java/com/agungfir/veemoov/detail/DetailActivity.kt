package com.agungfir.veemoov.detail

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.agungfir.core.domain.model.Movie
import com.agungfir.core.utils.Constants
import com.agungfir.core.utils.DateTimeUtils
import com.agungfir.veemoov.R
import com.agungfir.veemoov.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_DATA, Movie::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_DATA)
        }
        showDetailMovie(movie)
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.detailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.collapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT)
    }

    private fun showDetailMovie(movie: Movie?) {
        movie?.let {
            supportActionBar?.title = movie.title
            binding.content.tvOverviewDetail.text = movie.overview
            binding.content.tvReleaseDateDetail.text =
                DateTimeUtils.formatLongDate(movie.releaseDate)
            movie.title.let {
                binding.detailToolbar.title = it
                binding.content.tvTitleDetail.text = it
            }
            Glide
                .with(this)
                .load(Constants.ORIGINAL_IMAGE_URL + movie.backdropPath)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.shimmerBackdrop.apply {
                            stopShimmer()
                            hideShimmer()
                        }
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.shimmerBackdrop.apply {
                            stopShimmer()
                            hideShimmer()
                        }

                        return false
                    }
                })
                .into(binding.ivBackdropDetail)

            Glide
                .with(this)
                .load(Constants.IMAGE_URL + movie.posterPath)
                .into(binding.content.ivPosterDetail)

            var isFavorite = movie.isFavorite
            setStatusFavorite(isFavorite)
            binding.fabFavorite.setOnClickListener {
                isFavorite = !isFavorite
                detailViewModel.setFavoriteMovie(movie, isFavorite)
                setStatusFavorite(isFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        val drawable = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        drawable?.setColorFilter(
            ContextCompat.getColor(
                this,
                if (statusFavorite) R.color.pink else android.R.color.darker_gray
            ),
            PorterDuff.Mode.MULTIPLY
        )
        binding.fabFavorite.setImageDrawable(drawable)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)

    }

    companion object {
        const val EXTRA_DATA: String = "extra_data"
    }
}