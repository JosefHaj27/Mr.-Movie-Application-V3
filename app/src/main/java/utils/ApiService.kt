package utils

import com.example.mrmovieapplicationv3.model.movie.Movie
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface ApiService
{
    @GET("search/shows?q=movies") // HTTP requests and API endpoint
    fun getMovies(): Call<List<Movie>>
}