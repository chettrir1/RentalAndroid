package com.demo.rentaldemo.ui.feature.auth.login

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.demo.rentaldemo.R
import com.demo.rentaldemo.databinding.ActivityLoginBinding
import com.demo.rentaldemo.ui.base.BaseActivity
import com.demo.rentaldemo.ui.feature.auth.forgotpassword.VerifyEmailActivity
import com.demo.rentaldemo.ui.feature.auth.register.RegisterActivity
import com.demo.rentaldemo.ui.feature.main.landing.MainActivity
import com.demo.rentaldemo.ui.utils.ProgressDialog
import com.demo.rentaldemo.ui.utils.SharedPreferenceManager
import com.demo.rentaldemo.ui.utils.Status
import com.demo.rentaldemo.ui.utils.showToast
import www.sanju.motiontoast.MotionToastStyle

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    private var email = ""
    private var password = ""

    private lateinit var dialog: Dialog
    private lateinit var preference: SharedPreferenceManager

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
        preference = SharedPreferenceManager(this)

        if (preference.email?.isNotEmpty() == true && preference.password?.isNotEmpty() == true) {
            binding.cbRemember.isChecked = true
            binding.etEmail.setText(preference.email)
            binding.etPassword.setText(preference.password)
        }

        binding.cbRemember.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (!isChecked) {
                preference.email = ""
                preference.password = ""
            }
        }
        binding.tvForgotPassword.setOnClickListener {
            VerifyEmailActivity.start(this)
        }

        binding.btnLogin.setOnClickListener {
            if (isValid()) {
                viewModel.authenticateUser(email, password)
            }
        }

        binding.btnCreateAccount.setOnClickListener {
            RegisterActivity.start(this)
        }
    }

    private fun isValid(): Boolean {
        email = binding.etEmail.text.toString()
        password = binding.etPassword.text.toString()

        if (email.isEmpty()) {
            showToast("Warning", "Email field cannot be empty!", MotionToastStyle.WARNING)
            return false
        }

        if (password.isEmpty()) {
            showToast("Warning", "Password field cannot be empty!", MotionToastStyle.WARNING)
            return false
        }

        return true
    }


    override fun initObservers() {
        observeLoginResponse()
    }

    private fun observeLoginResponse() {
        viewModel.loginResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    showProgress()
                }

                Status.COMPLETE -> {
                    hideProgress()
                    showToast("Success", "User logged in Successfully!", MotionToastStyle.SUCCESS)
                    preference.accessToken = response.data?.accessToken
                    if (binding.cbRemember.isChecked) {
                        preference.email = email
                        preference.password = password
                    }

                    MainActivity.start(this)
                }

                Status.ERROR -> {
                    hideProgress()
                    showToast(
                        "Error",
                        "Something went wrong!",
                        MotionToastStyle.ERROR
                    )
                }
            }
        }
    }

    private fun showProgress() {
        dialog = ProgressDialog.progressDialog(this)
        dialog.show()
    }

    private fun hideProgress() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }
}