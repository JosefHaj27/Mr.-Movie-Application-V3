package com.example.mrmovieapplicationv3.ui.home

import android.content.Intent
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
import retrofit2.Call
import retrofit2.Response
import utils.ApiClient
import utils.GlobalKeys

class HomePageFragment : Fragment(), MovieAdapter.OnMovieItemClickListener {
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

    private fun initializing() {
        callingAPIForMoviesData()
    }

    private fun setupAdapter(movies: List<Movie>) {
        binding.recycleViewId.adapter = MovieAdapter(requireContext(), movies, this)
        binding.recycleViewId.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun callingAPIForMoviesData() {
        val call = ApiClient.apiService.getMovies()
        call.enqueue(object : retrofit2.Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful) {
                    val moviesBodyData = response.body()
                    moviesBodyData?.let {
                        setupAdapter(moviesBodyData)
                    }
                }
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMovieItemClick(movie: Movie) {
        val myIntent = Intent(requireContext(), DetailActivity::class.java)
        myIntent.putExtra(GlobalKeys.MOVIE_DATA, movie)
        startActivity(myIntent)
    }
}