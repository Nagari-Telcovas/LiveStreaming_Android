package com.example.livestreaming.login


import com.example.livestreaming.BaseActivity
import com.example.livestreaming.CommonMethods
import com.example.livestreaming.MainActivity
import com.example.livestreaming.R
import com.example.livestreaming.databinding.ActivityLoginBinding
import java.util.regex.Pattern

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate, R.string.login) {
    override fun initialization(bindingScreen: ActivityLoginBinding) {
        bindingScreen.loginButton.setOnClickListener {
            if (bindingScreen.phoneNumber.text.toString().isEmpty()){
                CommonMethods.showMessage(this, "Mobile number can't be Empty!")
            }else if (!isValidMobile(bindingScreen.phoneNumber.text.toString())){
                CommonMethods.showMessage(this, "Please enter valid mobile number!")
            }else{
                CommonMethods.intentActivity(this, MainActivity())
                finish()
            }
        }
    }

    private fun isValidMobile(phone: String): Boolean {
        return if (!Pattern.matches("[a-zA-Z]+", phone)) {
            phone.length in 7..14
        } else false
    }
}