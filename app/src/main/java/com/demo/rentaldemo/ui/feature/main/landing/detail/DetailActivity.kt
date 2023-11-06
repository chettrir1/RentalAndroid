package com.demo.rentaldemo.ui.feature.main.landing.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
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
        }
    }

    override fun layout() = R.layout.activity_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        Glide.with(binding.root.context)
            .load("https://fastly.picsum.photos/id/20/3670/2462.jpg?hmac=CmQ0ln-k5ZqkdtLvVO23LjVAEabZQx2wOaT4pyeG10I")
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.logo).into(binding.ivDetail)
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
                        adapter = HomeFacilitiesAdapter(it.toMutableList())
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
                        binding.rvNearestFacilities.adapter = nAdapter
                    }
                }

                Status.ERROR -> {

                }
            }
        }
    }
}
