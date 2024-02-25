package utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

object RetrofitClient {
    private const val BASE_URL = "https://api.tvmaze.com/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

object ApiClient {
    val apiService: ApiService by lazy {
        RetrofitClient.retrofit.create(ApiService::class.java)
    }
}