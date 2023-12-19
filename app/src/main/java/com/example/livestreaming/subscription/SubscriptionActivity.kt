package com.example.livestreaming.subscription

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.example.livestreaming.R
import com.example.livestreaming.paymentMethod.PaymentMethodActivity

class SubscriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscription)
        val subscribeButton = findViewById<AppCompatButton>(R.id.subscribeButton)
        val joinNow = findViewById<AppCompatButton>(R.id.joinNow)
        subscribeButton.setOnClickListener {
            val intentImg = Intent(this, PaymentMethodActivity::class.java)
            startActivity(intentImg)
            finish()
        }
        joinNow.setOnClickListener {
            val intentImg = Intent(this, PaymentMethodActivity::class.java)
            startActivity(intentImg)
            finish()
        }

    }
}