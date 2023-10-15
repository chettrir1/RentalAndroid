package com.demo.rentaldemo.ui.feature.auth.forgotpassword

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.demo.rentaldemo.R
import com.demo.rentaldemo.databinding.ActivityRegisterBinding
import com.demo.rentaldemo.databinding.ActivityVerifyEmailBinding
import com.demo.rentaldemo.ui.base.BaseActivity

class VerifyEmailActivity : BaseActivity<ActivityVerifyEmailBinding>() {

    private val viewModel: ForgotPasswordViewModel by viewModels()

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, VerifyEmailActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun layout() = R.layout.activity_verify_email

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvBackToLogin.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun initObservers() {
    }
}