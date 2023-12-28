package com.example.livestreaming.fragments.account

import androidx.lifecycle.MutableLiveData
import com.example.livestreaming.networkLibrary.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class LoginViewModel : BaseViewModel() {

    var loginResponse = MutableLiveData<Response<LoginOutput>>()
    var error = MutableLiveData<Throwable>()

   // fun loginApiInput(msisdn: String, fcmCode: String, password: String){
  //      val disposable: Disposable = apiInterface.getLoginApi(msisdn, password, fcmCode)
  //          .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
  //          .subscribe({ onSuccess(it) }, { onError(it) })
  //  }

    private fun onSuccess(it: Response<LoginOutput>) {
        loginResponse.value = it
    }

    //Error Result
    private fun onError(it: Throwable?) {
       // error.value = it
    }
}