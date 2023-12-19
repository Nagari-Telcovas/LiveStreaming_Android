package com.example.livestreaming.fragments.premium


import android.content.Intent
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.example.livestreaming.BaseFragment
import com.example.livestreaming.R
import com.example.livestreaming.paymentMethod.PaymentMethodActivity


class PremiumFragment : BaseFragment(R.layout.fragment_premium) {

    override fun initFragment(view: View) {
     //   val subscribeButton = view.findViewById<AppCompatButton>(R.id.subscribeButton)
        val joinNow = view.findViewById<AppCompatButton>(R.id.joinNow)
        val joinNow2 = view.findViewById<AppCompatButton>(R.id.joinNow2)
        joinNow.setOnClickListener {
            val intentImg = Intent(context, PaymentMethodActivity::class.java)
            startActivity(intentImg)
        }
        joinNow2.setOnClickListener {
            val intentImg = Intent(context, PaymentMethodActivity::class.java)
            startActivity(intentImg)
        }

    }

}