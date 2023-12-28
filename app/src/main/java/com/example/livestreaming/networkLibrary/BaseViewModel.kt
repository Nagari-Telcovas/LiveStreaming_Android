package com.example.livestreaming.networkLibrary


import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val apiInterface: ApiInterface by lazy {
        RetrofitUtil.apiService()
    }
}