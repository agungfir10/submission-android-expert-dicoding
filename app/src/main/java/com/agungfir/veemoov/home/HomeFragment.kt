package com.agungfir.veemoov.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agungfir.core.data.Resource
import com.agungfir.core.domain.model.Movie
import com.agungfir.core.ui.MovieAdapter
import com.agungfir.veemoov.databinding.FragmentHomeBinding
import com.agungfir.veemoov.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, it)
            startActivity(intent)
        }

        if (activity != null) {

            homeViewModel.movies.observe(viewLifecycleOwner) {
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> renderLoading()
                        is Resource.Error -> renderError()
                        is Resource.Success -> renderSuccess(it.data)
                    }
                }
            }
        }
    }

    private fun renderSuccess(data: List<Movie>?) {
        binding.apply {
            progressBar.visibility = View.GONE
            rvMovies.visibility = View.VISIBLE
            rvMovies.adapter = movieAdapter
        }
        movieAdapter.setData(data)

    }

    private fun renderError() {
    }

    private fun renderLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}