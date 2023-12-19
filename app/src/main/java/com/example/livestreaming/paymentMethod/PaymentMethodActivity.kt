package com.example.livestreaming.paymentMethod

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.example.livestreaming.CommonMethods
import com.example.livestreaming.R
import com.example.livestreaming.VideoDetailsActivity
import com.example.livestreaming.VideoListActivity

class PaymentMethodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_method)
        val payNow = findViewById<AppCompatButton>(R.id.payNow)
        payNow.setOnClickListener {
            CommonMethods.setSharedPreference(this, "Subscribed", "SubscribedPlan")
            val intentImg = Intent(this, VideoListActivity::class.java)
            startActivity(intentImg)
            finish()
        }
    }
}