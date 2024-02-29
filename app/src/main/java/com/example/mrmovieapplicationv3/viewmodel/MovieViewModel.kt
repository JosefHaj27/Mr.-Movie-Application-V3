package com.example.mrmovieapplicationv3.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mrmovieapplicationv3.model.data.Movie
import com.example.mrmovieapplicationv3.repository.network.ApiClient
import retrofit2.Call
import retrofit2.Response


class MovieViewModel : ViewModel() {
    private val TAG = "Movie_View_Model"
    private var _moviesMutableLiveData = MutableLiveData<List<Movie>>()
    private val moviesLiveData: LiveData<List<Movie>>
        get() = _moviesMutableLiveData

    init {
        callingAPIForMoviesData()
    }

    private fun callingAPIForMoviesData() {
        val call = ApiClient.apiService.getMovies()
        call.enqueue(object : retrofit2.Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful) {
                    val moviesBodyData = response.body()
                    moviesBodyData?.let {
                        _moviesMutableLiveData.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
                // TODO:: Also you can display a message that adhere the connection failed for the user in the app
            }
        })
    }

    fun getMovies(): LiveData<List<Movie>> = moviesLiveData
}