package com.agungfir.veemoov.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.agungfir.core.ui.MovieAdapter
import com.agungfir.veemoov.databinding.FragmentFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (activity != null) {

            favoriteViewModel.favoriteMovies.observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    binding.rvMovies.visibility = View.VISIBLE
                    movieAdapter = MovieAdapter()
                    movieAdapter.setData(it)
                    binding.rvMovies.adapter = movieAdapter
                }
            }
        }

        binding.fabFavoriteFeature.setOnClickListener {
            gotoFavoriteFeature()
        }
    }

    private fun gotoFavoriteFeature() {
        try {
            startActivity(Intent(requireActivity(), Class.forName("com.agungfir.favorite")))
        } catch (e: Exception) {
            Toast.makeText(requireActivity(), "Module not found", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}