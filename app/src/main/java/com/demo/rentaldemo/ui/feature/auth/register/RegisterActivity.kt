package com.demo.rentaldemo.ui.feature.auth.register

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.demo.rentaldemo.R
import com.demo.rentaldemo.databinding.ActivityLoginBinding
import com.demo.rentaldemo.ui.base.BaseActivity

class RegisterActivity : BaseActivity<ActivityLoginBinding>() {

    private val viewModel: RegisterViewModel by viewModels()

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, RegisterActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            activity.startActivity(intent)
            activity.finish()
        }
    }

    override fun layout() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initObservers() {
    }
}