package com.demo.rentaldemo.ui.feature.auth.register

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.demo.rentaldemo.R
import com.demo.rentaldemo.databinding.ActivityRegisterBinding
import com.demo.rentaldemo.ui.base.BaseActivity
import com.demo.rentaldemo.ui.utils.ProgressDialog
import com.demo.rentaldemo.ui.utils.Status
import com.demo.rentaldemo.ui.utils.showToast
import www.sanju.motiontoast.MotionToastStyle

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    private val viewModel: RegisterViewModel by viewModels()

    private var firstName = ""
    private var lastName = ""
    private var email = ""
    private var phone = ""
    private var password = ""
    private var confirmPassword = ""

    private lateinit var dialog: Dialog

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

        binding.btnRegister.setOnClickListener {
            if (isValid()) {
                viewModel.createUser(firstName, lastName, email, phone, password)
            }
        }

    }

    private fun isValid(): Boolean {
        firstName = binding.etFirstName.text.toString()
        lastName = binding.etLastName.text.toString()
        email = binding.etEmail.text.toString()
        phone = binding.etPhone.text.toString()
        password = binding.etPassword.text.toString()
        confirmPassword = binding.etConfirmPassword.text.toString()
        if (firstName.isEmpty()) {
            showToast("Warning", "First Name field cannot be empty!", MotionToastStyle.WARNING)
            return false
        }

        if (lastName.isEmpty()) {
            showToast("Warning", "Last Name field cannot be empty!", MotionToastStyle.WARNING)
            return false
        }

        if (email.isEmpty()) {
            showToast("Warning", "Email field cannot be empty!", MotionToastStyle.WARNING)
            return false
        }

        if (phone.isEmpty()) {
            showToast("Warning", "Phone field cannot be empty!", MotionToastStyle.WARNING)
            return false
        }

        if (password.isEmpty()) {
            showToast("Warning", "Password field cannot be empty!", MotionToastStyle.WARNING)
            return false
        }

        if (confirmPassword.isEmpty()) {
            showToast(
                "Warning",
                "Confirm Password field cannot be empty!",
                MotionToastStyle.WARNING
            )
            return false
        }

        if (password != confirmPassword) {
            showToast(
                "Warning",
                "Confirm Password and Password must be same!",
                MotionToastStyle.WARNING
            )
            return false
        }

        return true
    }

    override fun initObservers() {
        observeRegisterResponse()
    }

    private fun observeRegisterResponse() {
        viewModel.registerResponse.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    showProgress()
                }

                Status.COMPLETE -> {
                    hideProgress()
                    showToast("Success", "User created Successfully!", MotionToastStyle.SUCCESS)
                    onBackPressedDispatcher.onBackPressed()
                }

                Status.ERROR -> {
                    hideProgress()
                    showToast(
                        "Error",
                        "Error occurred while creating user!",
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