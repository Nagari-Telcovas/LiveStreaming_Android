package com.example.livestreaming.networkLibrary




import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object RetrofitUtil {

    fun apiService(baseUrl: String = NetworkConstants.BASE_URL,
        connectionTimeOutInSec: Long = 10,
        readTimeOutInSec: Long = 30,
        writeTimeOutInSec: Long = 60): ApiInterface {
        return getRetrofitClient(getokhttpClientBuilder(connectionTimeOutInSec, readTimeOutInSec, writeTimeOutInSec), baseUrl).create(ApiInterface::class.java)
    }

   /* fun apiChatDemo(baseUrl: String = NetworkConstants.CHAT_DEMO_BASE_URL,
                   connectionTimeOutInSec: Long = 10,
                   readTimeOutInSec: Long = 30,
                   writeTimeOutInSec: Long = 60): ApiInterface {
        return getRetrofitClient(getokhttpClientBuilder(connectionTimeOutInSec, readTimeOutInSec, writeTimeOutInSec), baseUrl).create(ApiInterface::class.java)
    }
*/

    fun getRetrofitClient(okHttpClientBuilder: OkHttpClient.Builder, baseUrl: String) = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addConverterFactory(ScalarsConverterFactory.create())
        .client(okHttpClientBuilder.build())
        .baseUrl(baseUrl)
        .build()

   // var httpCacheDirectory: File = File(MyApplication.appContext.cacheDir, "cache_file")

   // var cache = Cache(httpCacheDirectory, 20 * 1024 * 1024)



    fun getokhttpClientBuilder(connectTimeoutInSec: Long, readTimeoutInSec: Long, writeTimeoutInSec: Long): OkHttpClient.Builder {
        val okHttpClientBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
       // okHttpClientBuilder.cache(cache)
       /* okHttpClientBuilder.addInterceptor { chain ->
            val request: Request = chain.request().newBuilder().addHeader("loginkey",
                Constants.loginkey
            ).build()
            chain.proceed(request)
        }
        okHttpClientBuilder.addInterceptor { chain ->
            val request1: Request = chain.request().newBuilder().addHeader("headermsisdn", Constants.Loggedinmsisdn).build()
            chain.proceed(request1)
        }*/
        okHttpClientBuilder.connectTimeout(connectTimeoutInSec, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(readTimeoutInSec, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(writeTimeoutInSec, TimeUnit.SECONDS)
        return okHttpClientBuilder
    }




    /*fun getCacheEnabledRetrofit(context: Context){
        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(context.cacheDir, cacheSize)

        val okHttpClient = OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (CommonMethods.isInternetAvailable(context)!!)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                chain.proceed(request)
            }
            .build()
    }*/
}