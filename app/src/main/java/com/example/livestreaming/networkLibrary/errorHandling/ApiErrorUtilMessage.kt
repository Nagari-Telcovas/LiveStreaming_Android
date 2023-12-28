package com.example.livestreaming.networkLibrary.errorHandling


import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.livestreaming.CommonMethods
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException

object ApiErrorUtilMessage {
    val TAG = ApiErrorUtil::class.simpleName
    var message:String=""
    fun handlerGeneralError(context: Context?,v:View?, throwable: Throwable) {
        //Log.e(TAG, "Error: ${throwable.message}")
        //  throwable.printStackTrace()
        if (context == null) return

        when (throwable) {
            is HttpException -> {
                try {
                    Log.d("Error22:", "${throwable.code()}")
                    when (throwable.code()) {
                        400 -> {
                            val body = (throwable as HttpException).response()!!.errorBody()
                            val gson = Gson()
                            val adapter = gson.getAdapter<ErrorBean>(ErrorBean::class.java!!)
                            try {
                                val errorParser = adapter.fromJson(body!!.string())
                                message=errorParser.message
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                            Toast.makeText(context, message,Toast.LENGTH_SHORT).show()
                        }

                        401 -> {
                            val body = (throwable as HttpException).response()!!.errorBody()
                            Log.d("Error44:", "$body")
                            //val gson = Gson()
                            val gson = GsonBuilder().setLenient().create()
                            val adapter = gson.getAdapter<ErrorBean>(ErrorBean::class.java!!)
                            try {
                                val errorParser = adapter.fromJson(body!!.string())
                                Log.d("Error55:", "$errorParser")
                                message=errorParser.message
                                forceLogout(context)
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                            Log.d("Response401", message)
                            Toast.makeText(context, message,Toast.LENGTH_SHORT).show()
                            //logout(context)
                        }


                        403 -> {
                            val body = (throwable as HttpException).response()!!.errorBody()
                            val gson = Gson()
                            val adapter = gson.getAdapter<ErrorBean>(ErrorBean::class.java!!)
                            try {
                                val errorParser = adapter.fromJson(body!!.string())
                                message=errorParser.message

                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                            Toast.makeText(context, message,Toast.LENGTH_SHORT).show()
                        }

                        404 -> {
                            val body = (throwable as HttpException).response()!!.errorBody()
                            val gson = Gson()
                            val adapter = gson.getAdapter<ErrorBean>(ErrorBean::class.java!!)
                            try {
                                val errorParser = adapter.fromJson(body!!.string())
                                message=errorParser.message

                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                            Toast.makeText(context, message,Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            val body = (throwable as HttpException).response()!!.errorBody()
                            val gson = Gson()
                            val adapter = gson.getAdapter<ErrorBean>(ErrorBean::class.java!!)
                            try {
                                val errorParser = adapter.fromJson(body!!.string())
                                message=errorParser.message

                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                            // SnackbarUtils.displayError(view, throwable.message())
                            // Toast.makeText(context, message,Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (exception: Exception) {
                    Toast.makeText(
                        context,
                        "error_something_went_wrong_please_retry",
                        Toast.LENGTH_SHORT
                    ).show()
                    exception.printStackTrace()
                }
            }
            is ConnectException -> CommonMethods.showMessage(context, throwable.message!!.toString())
            is SocketTimeoutException -> CommonMethods.showMessage(context, throwable.message!!.toString())
            else -> CommonMethods.showMessage(context,  throwable.message!!.toString())
        }
    }

    /* private fun logout(context: Context) {
         // preventing from unnessary error if occur however didn't find any
         try {
             SharedPreferenceUtil.getInstance(context).deletePreferences()
             val intent = Intent(context, LoginActivity::class.java)
             intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
             context.startActivity(intent)
           *//*  if (context is LoginActivity) return
           RetrofitUtil.createBaseApiService().logout(
                    access_token = SharedPreferenceUtil.getInstance(context).access_token
            ).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {},
                            { forceLogout(context) },
                            { forceLogout(context) }
                    )*//*
        } catch (e: Exception) {
        }
    }*/
    private fun forceLogout(context: Context) {
        /* SharedPreferenceUtil.getInstance(context).isLogin = false
         context.startActivity(Intent(context, SignInActivity::class.java).apply {
         })
         (context as AppCompatActivity).finish()*/
    }

}