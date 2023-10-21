package com.demo.rentaldemo.ui.feature.main.landing.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.rentaldemo.R
import com.demo.rentaldemo.databinding.FragmentHomeBinding
import com.demo.rentaldemo.ui.base.BaseFragment
import com.demo.rentaldemo.ui.feature.main.landing.CategoriesAdapter
import com.demo.rentaldemo.ui.feature.main.landing.MainViewModel
import com.demo.rentaldemo.ui.feature.main.landing.RecentlyUpdatedAdapter
import com.demo.rentaldemo.ui.utils.Status

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: MainViewModel by viewModels()

    private var adapter: CategoriesAdapter? = null
    private var rAdapter: RecentlyUpdatedAdapter? = null

    override fun layout(): Int = R.layout.fragment_home


    companion object {
        fun getInstance(): Fragment {
            return HomeFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCategoriesResponse()
        viewModel.getRecentlyUpdatedResponse()
    }

    override fun initObservers() {
        observeGroceryResponse()
        observeRecentlyUpdatedResponse()
    }

    private fun observeGroceryResponse() {
        viewModel.categoryResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {

                }

                Status.COMPLETE -> {
                    response.data?.let {
                        val layoutManager = LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                        adapter = CategoriesAdapter(it.toMutableList()) { response -> }
                        binding.rvCategories.layoutManager = layoutManager
                        binding.rvCategories.adapter = adapter
                    }
                }

                Status.ERROR -> {

                }
            }
        }
    }

    private fun observeRecentlyUpdatedResponse() {
        viewModel.recentResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {

                }

                Status.COMPLETE -> {
                    response.data?.let {
                        rAdapter = RecentlyUpdatedAdapter(it.toMutableList()) { response -> }
                        binding.rvRecentlyUpdated.adapter = rAdapter
                    }
                }

                Status.ERROR -> {

                }
            }
        }
    }

}