package com.demo.rentaldemo.ui.feature.auth.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.demo.rentaldemo.R
import com.demo.rentaldemo.databinding.ActivityLoginBinding
import com.demo.rentaldemo.ui.base.BaseActivity
import com.demo.rentaldemo.ui.feature.auth.forgotpassword.VerifyEmailActivity
import com.demo.rentaldemo.ui.feature.auth.register.RegisterActivity
import com.demo.rentaldemo.ui.feature.main.landing.MainActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, LoginActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            activity.startActivity(intent)
            activity.finish()
        }
    }

    override fun layout() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvForgotPassword.setOnClickListener {
            VerifyEmailActivity.start(this)
        }

        binding.btnLogin.setOnClickListener {
            MainActivity.start(this)
        }

        binding.btnCreateAccount.setOnClickListener {
            RegisterActivity.start(this)
        }
    }

    override fun initObservers() {
    }
}