package com.example.mrmovieapplicationv3.ui.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mrmovieapplicationv3.ui.details.DetailActivity
import com.example.mrmovieapplicationv3.databinding.FragmentHomePageBinding
import com.example.mrmovieapplicationv3.model.movie.Movie
import com.example.mrmovieapplicationv3.ui.common.MovieAdapter
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject
import utils.MovieSharedPreference
import java.lang.reflect.Type

class HomePageFragment : Fragment(), MovieAdapter.OnMovieItemClickListener
{
    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!

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
    }

    private fun initializeAllListsUsingGson(): List<Movie>
    {
        val sharePref = requireContext().getSharedPreferences(MovieSharedPreference.SHARE_PREF, Context.MODE_PRIVATE)
        var moviesData: String? = sharePref.getString("all_movies", null)
        val gson = Gson()
//        println("first of the class data: $moviesData")

        if (moviesData == null)
        {
            val editor: SharedPreferences.Editor = sharePref.edit()
            val myData = gson.toJson(Movie.initializeAllLists(requireContext()))
            editor.putString("all_movies", myData)
            editor.apply()
//            println("in the if statement: $myData")

            return Movie.initializeAllLists(requireContext())
        }
        else
        {
            val type: Type = object : TypeToken<List<Movie?>?>() {}.type
            val movieDataFromJson = gson.fromJson<List<Movie>>(moviesData, type)
//             val movieDataFromJson: List<Movie> = gson.fromJson(moviesData, type)
//            both works the same.
//            println("in else, $newData")

            return movieDataFromJson
        }
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }


    override fun onMovieItemClick(movie: Movie)
    {
        val myIntent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra("m_name", movie.movieName)
            putExtra("m_rating", movie.movieRating)
            putExtra("m_len", movie.movieLength)
            putExtra("m_gen", movie.movieGenre)
            putExtra("m_img", movie.moviePoster)
            putExtra("m_des", movie.movieDescription)
        }
        startActivity(myIntent)
    }

    override fun onBookmarkedImageClick(movie: Movie) {
        TODO("Not yet implemented")
    }
}