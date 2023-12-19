package com.example.livestreaming

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.livestreaming.onBoardScreen.OnBoardingActivity

class SplashActivity : AppCompatActivity() {
    private val IMAGE_TIME: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        displayImage()
    }

    private fun displayImage() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish()
        }, IMAGE_TIME)
    }
}