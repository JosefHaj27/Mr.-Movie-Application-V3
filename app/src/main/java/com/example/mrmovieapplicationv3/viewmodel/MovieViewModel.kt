package com.example.mrmovieapplicationv3.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mrmovieapplicationv3.model.data.Movie
import com.example.mrmovieapplicationv3.model.data.Show
import com.example.mrmovieapplicationv3.repository.network.ApiClient
import retrofit2.Call
import retrofit2.Response
import java.util.Collections


class MovieViewModel : ViewModel() {

    private val TAG = "Movie_View_Model"
    private var _moviesMutableLiveData = MutableLiveData<List<Show>>()
    private val moviesLiveData: LiveData<List<Show>>
        get() = _moviesMutableLiveData

    fun callingAPIForMoviesData(searchQuery: String? = "movies") {
        val call = ApiClient.apiService.getMovies(searchQuery)
        call.enqueue(object : retrofit2.Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful) {
                    val moviesBodyData = response.body()
                    moviesBodyData?.let {
                        val showsArray = moviesBodyData.map {
                            it.show
                        }
                        _moviesMutableLiveData.value =
                            (_moviesMutableLiveData.value)?.plus(showsArray) ?: showsArray
                    }
                }
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
                // TODO:: Also you can display a message that adhere the connection failed for the user in the app
            }
        })
    }


    fun callingAPIForShowsPagesData(pageNumber: Int) {
        val call = ApiClient.apiService.getShows(pageNumber)
        call.enqueue(object : retrofit2.Callback<List<Show>> {
            override fun onResponse(call: Call<List<Show>>, response: Response<List<Show>>) {
                if (response.isSuccessful) {
                    val moviesBodyData = response.body()
                    moviesBodyData?.let {
                        _moviesMutableLiveData.value =
                            (_moviesMutableLiveData.value)?.plus(it) ?: it
                    }
                }
            }

            override fun onFailure(call: Call<List<Show>>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
                // TODO:: Also you can display a message that adhere the connection failed for the user in the app
            }
        })
    }

    fun getMovies(): LiveData<List<Show>> = moviesLiveData
    fun shuffleMovies(movies: List<Show>) = Collections.shuffle(movies)
}