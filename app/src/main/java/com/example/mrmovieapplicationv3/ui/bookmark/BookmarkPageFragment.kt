package com.example.mrmovieapplicationv3.ui.bookmark

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mrmovieapplicationv3.databinding.FragmentBookmarkPageBinding
import com.example.mrmovieapplicationv3.model.movie.Movie
import com.example.mrmovieapplicationv3.ui.common.MovieAdapter
import com.example.mrmovieapplicationv3.ui.details.DetailActivity
import utils.GlobalKeys
import utils.MovieSharedPreference

class BookmarkPageFragment : Fragment(), MovieAdapter.OnMovieItemClickListener {
    private var _binding: FragmentBookmarkPageBinding? = null
    private val binding get() = _binding!!
    private val intentFilter = IntentFilter(GlobalKeys.BROADCAST_ACTION)
    private val movies = mutableListOf<Movie>()
    private var movieAdapter: MovieAdapter? = null

    private val receiver = object :BroadcastReceiver(){

        override fun onReceive(context: Context?, intent: Intent?) {
            val movie: Movie? = if (SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                intent?.getParcelableExtra(GlobalKeys.MOVIE_DATA, Movie::class.java)
            } else{
                intent?.getParcelableExtra(GlobalKeys.MOVIE_DATA)
            }
            if (movie != null) {
                if (movie.isBookmarked)
                {
                    movies.add(movie)
                }
                else{
                    movies.removeIf {
                        it.movieID == movie.movieID
                    }
                }
                movieAdapter?.notifyDataSetChanged()
            }
            else
            {
                println("null movie data")
            }
            println("In onReceive method ${movie?.movieName}")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkPageBinding.inflate(inflater, container, false)
        initializing()
        return binding.root
    }


    override fun onStart() {
        super.onStart()
        context?.registerReceiver(receiver, intentFilter, Context.RECEIVER_EXPORTED)
    }

    override fun onDestroy() {
        super.onDestroy()
        context?.unregisterReceiver(receiver)
    }
    private fun initializing() {
        movieAdapter = MovieAdapter(requireContext(), checkBookmarkedMovie(), this)
        binding.recycleViewBookmarkId.adapter = movieAdapter
        binding.recycleViewBookmarkId.layoutManager = LinearLayoutManager(requireContext())
       // receive broadcast to construct the list of movies and send it the adapter.
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

    private fun checkBookmarkedMovie(): List<Movie> {

        val fromJsonData = MovieSharedPreference.getAllMovies(requireContext())
        for (movie in fromJsonData)
        {
            if (movie.isBookmarked)
            {
                movies.add(movie)
            }
        }

        println("all movies are $movies")
        return movies
    }
}