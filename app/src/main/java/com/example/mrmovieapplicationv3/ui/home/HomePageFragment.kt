package com.example.mrmovieapplicationv3.ui.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mrmovieapplicationv3.databinding.FragmentHomePageBinding
import com.example.mrmovieapplicationv3.model.movie.Movie
import com.example.mrmovieapplicationv3.ui.common.MovieAdapter
import com.example.mrmovieapplicationv3.ui.details.DetailActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Response
import utils.ApiClient
import utils.GlobalKeys
import utils.MovieSharedPreference
import java.lang.reflect.Type
import javax.security.auth.callback.Callback

class HomePageFragment : Fragment(), MovieAdapter.OnMovieItemClickListener
{
    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!
    private val TAG = "HOME_PAGE_FRAGMENT"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        initializing()
        return binding.root
    }

    private fun initializing()
    {
        binding.recycleViewId.adapter = MovieAdapter(requireContext(), initializeAllListsUsingGson(), this)
        binding.recycleViewId.layoutManager = LinearLayoutManager(requireContext())
        callingAPIForMoviesData()
    }

    private fun callingAPIForMoviesData()
    {
        val call = ApiClient.apiService.getMovies()
        call.enqueue(object : retrofit2.Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if(response.isSuccessful)
                {
                    val moviesBodyData = response.body()
                    Log.d(TAG, "response: ${response.body()?.count()}")
                    Log.d(TAG, "list of the body: ${response.body()?.toList()}")
                    if (moviesBodyData != null) {
                        for (i in moviesBodyData) {
                            Log.d(TAG, "onResponse: ${i}")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
//                println("Failed to make a request")
                Log.d(TAG, "t: ${t.message}")
            }
        })
    }

    private fun initializeAllListsUsingGson(): List<Movie>
    {
        val sharePref = requireContext().getSharedPreferences(MovieSharedPreference.SHARE_PREF, Context.MODE_PRIVATE)
        var moviesData: String? = sharePref.getString(GlobalKeys.ALL_MOVIES, null)
        val gson = Gson()

        if (moviesData == null)
        {
            val editor: SharedPreferences.Editor = sharePref.edit()
            val moviesDataJson = gson.toJson(Movie.initializeAllLists(requireContext()))
            editor.putString(GlobalKeys.ALL_MOVIES, moviesDataJson)
            editor.apply()
            return Movie.initializeAllLists(requireContext())
        }
        else
        {
            val type: Type = object : TypeToken<List<Movie?>?>() {}.type
            return gson.fromJson(moviesData, type)
        }
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }

    override fun onMovieItemClick(movie: Movie)
    {
        val myIntent = Intent(requireContext(), DetailActivity::class.java)
        myIntent.putExtra(GlobalKeys.MOVIE_DATA, movie)
        startActivity(myIntent)
    }

}