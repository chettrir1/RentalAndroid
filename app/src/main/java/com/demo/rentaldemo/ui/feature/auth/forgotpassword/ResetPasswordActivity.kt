package com.demo.rentaldemo.ui.feature.auth.forgotpassword

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.demo.rentaldemo.R
import com.demo.rentaldemo.databinding.ActivityResetPasswordBinding
import com.demo.rentaldemo.ui.base.BaseActivity
import com.demo.rentaldemo.ui.feature.auth.login.LoginActivity

class ResetPasswordActivity : BaseActivity<ActivityResetPasswordBinding>() {

    private val viewModel: ForgotPasswordViewModel by viewModels()

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, ResetPasswordActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun layout() = R.layout.activity_reset_password

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnConfirm.setOnClickListener {
            LoginActivity.start(this)
        }
    }

    override fun initObservers() {
    }
}