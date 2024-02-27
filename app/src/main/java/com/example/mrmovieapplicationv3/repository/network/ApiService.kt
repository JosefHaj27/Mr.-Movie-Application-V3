package com.example.mrmovieapplicationv3.repository.network

import com.example.mrmovieapplicationv3.model.data.Movie
import retrofit2.Call
import retrofit2.http.GET

interface ApiService
{
    @GET("search/shows?q=movies") // HTTP requests method and API endpoint
    fun getMovies(): Call<List<Movie>>
}