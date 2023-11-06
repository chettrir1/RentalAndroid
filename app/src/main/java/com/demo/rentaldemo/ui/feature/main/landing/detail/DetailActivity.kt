package com.demo.rentaldemo.ui.feature.main.landing.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.rentaldemo.R
import com.demo.rentaldemo.databinding.ActivityDetailBinding
import com.demo.rentaldemo.ui.base.BaseActivity
import com.demo.rentaldemo.ui.feature.main.landing.MainViewModel
import com.demo.rentaldemo.ui.utils.Status


class DetailActivity : BaseActivity<ActivityDetailBinding>() {
    private val viewModel: MainViewModel by viewModels()
    private var adapter: HomeFacilitiesAdapter? = null
    private var nAdapter: NearPublicFacilitiesAdapter? = null

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, DetailActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
    }

    override fun layout() = R.layout.activity_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getHomeFacilitiesResponse()
        viewModel.getNearPublicFacilitiesResponse()

    }

    override fun initObservers() {
        observeHomeFacilitiesResponse()
        observeNearPublicFacilitiesResponse()
    }

    private fun observeHomeFacilitiesResponse() {
        viewModel.homeFacilitiesResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {

                }

                Status.COMPLETE -> {
                    response.data?.let {
                        val layoutManager = LinearLayoutManager(
                            this,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                        adapter = HomeFacilitiesAdapter(it.toMutableList())
                        binding.rvHomeFacilities.layoutManager = layoutManager
                        binding.rvHomeFacilities.adapter = adapter
                    }
                }

                Status.ERROR -> {

                }
            }
        }
    }

    private fun observeNearPublicFacilitiesResponse() {
        viewModel.nearPublicFacilitiesResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {

                }

                Status.COMPLETE -> {
                    response.data?.let {
                        nAdapter = NearPublicFacilitiesAdapter(it.toMutableList())
                        binding.rvNearestFacilities.adapter = adapter
                    }
                }

                Status.ERROR -> {

                }
            }
        }
    }
}
