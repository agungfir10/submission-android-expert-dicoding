package com.agungfir.veemoov.favorite

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.agungfir.veemoov.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGotoFavoriteFeature.setOnClickListener {
            gotoFavoriteFeature()
        }
    }

    private fun gotoFavoriteFeature() {
        try {
            moveToFavoriteActivity()
        } catch (e: Exception) {
            Toast.makeText(requireActivity(), "Module not found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun moveToFavoriteActivity() {
        val uri = Uri.parse("veemoov://favorite")
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}