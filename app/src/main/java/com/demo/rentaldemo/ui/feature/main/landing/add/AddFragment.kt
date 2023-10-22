package com.demo.rentaldemo.ui.feature.main.landing.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.demo.rentaldemo.R
import com.demo.rentaldemo.databinding.FragmentAddBinding
import com.demo.rentaldemo.ui.base.BaseFragment
import com.demo.rentaldemo.ui.feature.main.landing.MainViewModel

class AddFragment : BaseFragment<FragmentAddBinding>() {

    private val viewModel: MainViewModel by viewModels()

    override fun layout(): Int = R.layout.fragment_add


    companion object {
        fun getInstance(): Fragment {
            return AddFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initObservers() {
    }

}