package com.example.mrmovieapplicationv3.ui.bookmark

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mrmovieapplicationv3.ui.details.DetailActivity
import com.example.mrmovieapplicationv3.ui.common.MovieAdapter
import com.example.mrmovieapplicationv3.databinding.FragmentBookmarkPageBinding
import com.example.mrmovieapplicationv3.model.movie.Movie
import utils.MovieSharedPreference

class BookmarkPageFragment : Fragment(), MovieAdapter.OnMovieItemClickListener
{
    private var _binding: FragmentBookmarkPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        _binding = FragmentBookmarkPageBinding.inflate(inflater, container, false)
        initializing()

        return binding.root
    }
    private fun initializing()
    {
        binding.recycleViewBookmarkId.adapter = MovieAdapter(requireContext(), checkBookmarkedMovie(), this)
        binding.recycleViewBookmarkId.layoutManager = LinearLayoutManager(requireContext())
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
            putExtra("movie_bookmarked", movie.isBookmarked)
        }
        startActivity(myIntent)
    }

    override fun onBookmarkedImageClick(movie: Movie)
    {
        //val x = MovieSharedPreference.bookmarkThisMovie(requireContext(), movie.movieID)
    }

    private fun checkBookmarkedMovie(): List<Movie>
    {
        val bookmarkedMovies = mutableListOf<Movie>()

        for (movie in Movie.initializeAllLists(requireContext()))
        {
            if (movie.isBookmarked)
            {
                bookmarkedMovies.add(movie)
            }
        }
        return bookmarkedMovies
    }
}