package com.demo.rentaldemo.ui.feature.main.landing.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.demo.rentaldemo.R
import com.demo.rentaldemo.databinding.FragmentProfileBinding
import com.demo.rentaldemo.ui.base.BaseFragment
import com.demo.rentaldemo.ui.feature.main.landing.MainViewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private val viewModel: MainViewModel by viewModels()

    override fun layout(): Int = R.layout.fragment_profile


    companion object {
        fun getInstance(): Fragment {
            return ProfileFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initObservers() {
    }

}