package com.example.mrmovieapplicationv3

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mrmovieapplicationv3.databinding.FragmentBookmarkPageBinding

class BookmarkPageFragment : Fragment(), MovieAdapter.OnMovieItemClickListener
{
    private var _binding: FragmentBookmarkPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        _binding = FragmentBookmarkPageBinding.inflate(inflater, container, false)

        binding.recycleViewBookmarkId.adapter = MovieAdapter(requireContext(), HomePageFragment.initializeAllLists(requireContext()), this)
        binding.recycleViewBookmarkId.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }

    override fun onMovieItemClick(movie: MovieData)
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
}