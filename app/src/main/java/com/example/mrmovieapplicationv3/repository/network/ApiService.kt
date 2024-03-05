package com.example.mrmovieapplicationv3.repository.network

import com.example.mrmovieapplicationv3.model.data.Movie
import com.example.mrmovieapplicationv3.model.data.Show
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/shows") // HTTP requests method and API endpoint
    fun getMovies(@Query("q") value: String?): Call<List<Movie>>

    @GET("shows")
    fun getShows(@Query("page") page: Int): Call<List<Show>>
}