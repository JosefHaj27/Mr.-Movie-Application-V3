package com.example.mrmovieapplicationv3.ui.bookmark

import android.content.Intent
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkPageBinding.inflate(inflater, container, false)

        return binding.root
    }

//  TODO:: needs fix in future! using BroadcastReceiver
    override fun onResume() {
        super.onResume()
        initializing() // TODO:: move to onCreateView() method then implements BroadcastReceiver.
    }

    // receive broadcast to construct the list of movies and send it the adapter.
    private fun initializing() {
        binding.recycleViewBookmarkId.adapter =
            MovieAdapter(requireContext(), checkBookmarkedMovie(), this)
        binding.recycleViewBookmarkId.layoutManager = LinearLayoutManager(requireContext())
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
        val bookmarkedMovies = mutableListOf<Movie>()

        val fromJsonData = MovieSharedPreference.getAllMovies(requireContext())
        for (movie in fromJsonData)
        {
            if (movie.isBookmarked)
            {
                bookmarkedMovies.add(movie)
            }
        }
        return bookmarkedMovies
    }
}