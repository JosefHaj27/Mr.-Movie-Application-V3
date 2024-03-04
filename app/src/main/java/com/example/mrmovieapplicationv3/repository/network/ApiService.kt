package com.example.mrmovieapplicationv3.repository.network

import com.example.mrmovieapplicationv3.model.data.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/shows") // HTTP requests method and API endpoint
    fun getMovies(@Query("q") value: String?): Call<List<Movie>>
}