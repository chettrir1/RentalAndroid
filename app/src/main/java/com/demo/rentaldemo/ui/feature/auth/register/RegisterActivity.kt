package com.demo.rentaldemo.ui.feature.auth.register

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.demo.rentaldemo.R
import com.demo.rentaldemo.databinding.ActivityRegisterBinding
import com.demo.rentaldemo.ui.base.BaseActivity

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    private val viewModel: RegisterViewModel by viewModels()

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, RegisterActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun layout() = R.layout.activity_register

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvBackToLogin.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun initObservers() {
    }
}