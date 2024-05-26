package ru.alexsergeev.express.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.POST
import ru.alexsergeev.express.BuildConfig
import ru.alexsergeev.express.dto.Order
import java.util.concurrent.TimeUnit

val BASE_URL = "http://185.211.170.219:8080/api/"

val retrofit = Retrofit.Builder()
    .client(
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .run {
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                } else {
                    this
                }
            }
            .build()
    )
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface ApiService {
    @POST("order")
    fun makeOrder(@Body order: Order): Call<Order>
}

object OrderApi {
    val retrofitService: ApiService by lazy {
        retrofit.create()
    }
}