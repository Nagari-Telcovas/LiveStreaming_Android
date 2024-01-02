package com.example.livestreaming.login


import android.content.Intent
import com.example.livestreaming.BaseActivity
import com.example.livestreaming.MainActivity
import com.example.livestreaming.R
import com.example.livestreaming.databinding.ActivityLoginBinding
import com.example.livestreaming.onBoardScreen.OnBoardingActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate, R.string.login) {
    override fun initialization(bindingScreen: ActivityLoginBinding) {
        bindingScreen.loginButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}