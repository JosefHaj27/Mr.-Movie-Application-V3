package com.example.mrmovieapplicationv3.view.ui.bookmark

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mrmovieapplicationv3.databinding.FragmentBookmarkPageBinding
import com.example.mrmovieapplicationv3.model.data.Movie
import com.example.mrmovieapplicationv3.model.adapter.MovieAdapter
import com.example.mrmovieapplicationv3.view.ui.details.DetailActivity
import com.example.mrmovieapplicationv3.utils.GlobalKeys

class BookmarkPageFragment : Fragment(), MovieAdapter.OnMovieItemClickListener {
    private var _binding: FragmentBookmarkPageBinding? = null
    private val binding get() = _binding!!
    private val intentFilter = IntentFilter(GlobalKeys.BROADCAST_ACTION)
    private val movies = mutableListOf<Movie>()
    private var movieAdapter: MovieAdapter? = null
    private val TAG = "BOOKMARK_PAGE_FRAGMENT"

    private val receiver = object : BroadcastReceiver(){
        
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d(TAG, "onReceive: ")
            val movie: Movie? = if (SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                intent?.getParcelableExtra(GlobalKeys.MOVIE_DATA, Movie::class.java)
            } else{
                intent?.getParcelableExtra(GlobalKeys.MOVIE_DATA)
            }
            if(intent?.action == "com.example.mrmovieapplicationv3.bookmark_movie" )
            {
                Log.d(TAG, "onReceive: intent action")
            }
            if (movie != null) {
                Log.d(TAG, "onReceive movies: $movies")
                Log.d(TAG, "onReceive movie: $movie")
                if (movie.isBookmarked)
                {
                    movies.add(movie)
                    Log.d(TAG, "onReceive movies: $movies")
                    Log.d(TAG, "onReceive movie: $movie")
                }
//                else{
//                    movies.removeIf {
//                        it.show.id == movie.show.id
//                    }
//                }
                movieAdapter?.notifyDataSetChanged()
            }
//            else
//            {
//                println("null movie data")
//            }
            println("In onReceive method $movie")
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
        Log.d(TAG, "initializing: ${checkBookmarkedMovie()}")
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
//        movies.clear() // To clear movies list so no duplicate appears.
//        val fromJsonData = MovieSharedPreference.getAllMovies(requireContext())
//        for (movie in fromJsonData)
//        {
//            if (movie.isBookmarked)
//            {
//                movies.add(movie)
//            }
//        }

//        println("all movies are $movies")
        return movies
    }
}