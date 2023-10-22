package com.demo.rentaldemo.ui.feature.main.landing.favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.demo.rentaldemo.R
import com.demo.rentaldemo.databinding.FragmentFavouritesBinding
import com.demo.rentaldemo.ui.base.BaseFragment
import com.demo.rentaldemo.ui.feature.main.landing.MainViewModel
import com.demo.rentaldemo.ui.utils.Status

class FavouritesFragment : BaseFragment<FragmentFavouritesBinding>() {

    private val viewModel: MainViewModel by viewModels()

    private var adapter: FavouritesAdapter? = null

    override fun layout(): Int = R.layout.fragment_favourites


    companion object {
        fun getInstance(): Fragment {
            return FavouritesFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavouritesResponse()
    }

    override fun initObservers() {
        observeFavoritesResponse()
    }

    private fun observeFavoritesResponse() {
        viewModel.favouriteResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {

                }

                Status.COMPLETE -> {
                    response.data?.let {
                        adapter = FavouritesAdapter(it.toMutableList()) { response -> }
                        binding.rvFavourites.adapter = adapter
                    }
                }

                Status.ERROR -> {

                }
            }
        }
    }

}