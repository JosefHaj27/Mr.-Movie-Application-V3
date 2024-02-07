package com.example.mrmovieapplicationv3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mrmovieapplicationv3.databinding.FragmentHomePageBinding
//import com.example.mrmovieapplicationv3.R.*

class HomePageFragment : Fragment(), MovieAdapter.OnMovieItemClickListener
{
    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)

        binding.recycleViewId.adapter = MovieAdapter(requireContext(), initializeAllLists(requireContext()), this)
        binding.recycleViewId.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }

    companion object{

        fun initializeAllLists(context: Context): List<MovieData>
        {
            val names = context.resources.getStringArray(R.array.movie_names).toList()
            val desc = context.resources.getStringArray(R.array.movie_descriptions).toList()
            val rating = context.resources.getStringArray(R.array.movie_rating).toList()
            val genre =  context.resources.getStringArray(R.array.movie_genre).toList()
            val length = context.resources.getStringArray(R.array.movie_length).toList()
            val images = allImages()

            val movies = mutableListOf<MovieData>()
            for (i in 0..4) // number of movies exists
            {
                movies.add(MovieData(names[i], desc[i], rating[i], genre[i], length[i], images[i]))
            }
            return movies
        }
        fun allImages(): List<Int> {
            return listOf(
                R.drawable.avengers,
                R.drawable.interstellar,
                R.drawable.matrix,
                R.drawable.leon_the_professional,
                R.drawable.the_godfather,
            )
        }
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