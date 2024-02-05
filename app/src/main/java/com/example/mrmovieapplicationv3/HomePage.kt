package com.example.mrmovieapplicationv3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mrmovieapplicationv3.databinding.FragmentHomePageBinding
import com.example.mrmovieapplicationv3.databinding.OneRowLayoutBinding

class HomePage : Fragment()
{
    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)

        binding.recycleViewId.adapter = MyAdapter(requireContext(), initializeAllLists())
        binding.recycleViewId.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

    private fun allImages(): List<Int>
    {
        return listOf(
            R.drawable.avengers,
            R.drawable.interstellar,
            R.drawable.matrix,
            R.drawable.leon_the_professional,
            R.drawable.the_godfather,
        )
    }

    private fun initializeAllLists(): List<MovieData>
    {
        val names = resources.getStringArray(R.array.movie_names).toList()
        val desc = resources.getStringArray(R.array.movie_descriptions).toList()
        val rating = resources.getStringArray(R.array.movie_rating).toList()
        val genre = resources.getStringArray(R.array.movie_genre).toList()
        val length = resources.getStringArray(R.array.movie_length).toList()
        val images = allImages()

        val movies = mutableListOf<MovieData>()
        for (i in 0..4) // number of movies exists
        {
            movies.add(MovieData(names[i], desc[i], rating[i], genre[i], length[i], images[i]))
        }
        return movies
    }
}