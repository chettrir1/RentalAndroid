package com.demo.rentaldemo.ui.feature.main.landing

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.fragment.app.Fragment
import com.demo.rentaldemo.R
import com.demo.rentaldemo.databinding.ActivityMainBinding
import com.demo.rentaldemo.ui.base.BaseActivity
import com.demo.rentaldemo.ui.feature.main.landing.add.AddFragment
import com.demo.rentaldemo.ui.feature.main.landing.favourites.FavouritesFragment
import com.demo.rentaldemo.ui.feature.main.landing.home.HomeFragment
import com.demo.rentaldemo.ui.feature.main.landing.profile.ProfileFragment


class MainActivity : BaseActivity<ActivityMainBinding>() {

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, MainActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            activity.startActivity(intent)
            activity.finish()
        }
    }

    override fun layout() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDefaultFragment(savedInstanceState)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    val fragment = HomeFragment.getInstance()
                    addFragment(fragment)
                    return@setOnItemSelectedListener true
                }

                R.id.action_favourite -> {
                    val fragment = FavouritesFragment.getInstance()
                    addFragment(fragment)
                    return@setOnItemSelectedListener true
                }

                R.id.action_add -> {
                    val fragment = AddFragment.getInstance()
                    addFragment(fragment)
                    return@setOnItemSelectedListener true
                }

                R.id.action_profile -> {
                    val fragment = ProfileFragment.getInstance()
                    addFragment(fragment)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    override fun initObservers() {

    }

    private fun setDefaultFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.main_container,
                    HomeFragment.getInstance()
                ).commit()
        }
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    override fun getOnBackInvokedDispatcher(): OnBackInvokedDispatcher {
        if (binding.bottomNavigationView.selectedItemId == R.id.action_home) {
            onBackPressedDispatcher.onBackPressed()
            finish()
        } else {
            binding.bottomNavigationView.selectedItemId = R.id.action_home
        }

        return super.getOnBackInvokedDispatcher()

    }
}
