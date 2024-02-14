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

    //    TODO:: needs fix in future!
    override fun onResume() {
        super.onResume()
        initializing()
    }

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
        val myIntent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(GlobalKeys.MOVIE_NAME, movie.movieName)
            putExtra(GlobalKeys.MOVIE_RATING, movie.movieRating)
            putExtra(GlobalKeys.MOVIE_LENGTH, movie.movieLength)
            putExtra(GlobalKeys.MOVIE_GENRE, movie.movieGenre)
            putExtra(GlobalKeys.MOVIE_POSTER, movie.moviePoster)
            putExtra(GlobalKeys.MOVIE_DESCRIPTION, movie.movieDescription)
            putExtra(GlobalKeys.MOVIE_ID, movie.movieID)
//            putExtra("movie_bookmarked", movie.isBookmarked)
        }
        startActivity(myIntent)
    }

    private fun checkBookmarkedMovie(): List<Movie> {
        val bookmarkedMovies = mutableListOf<Movie>()

        val fromJsonData = MovieSharedPreference.getAllMovies(requireContext())
        for (movie in fromJsonData) {
            if (movie.isBookmarked) {
                bookmarkedMovies.add(movie)
            }
        }
        return bookmarkedMovies
    }
}