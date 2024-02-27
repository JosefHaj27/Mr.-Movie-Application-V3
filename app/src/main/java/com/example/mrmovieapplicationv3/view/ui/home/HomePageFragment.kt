package com.example.mrmovieapplicationv3.view.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mrmovieapplicationv3.databinding.FragmentHomePageBinding
import com.example.mrmovieapplicationv3.model.adapter.MovieAdapter
import com.example.mrmovieapplicationv3.model.data.Movie
import com.example.mrmovieapplicationv3.ui.details.DetailActivity
import com.example.mrmovieapplicationv3.utils.GlobalKeys
import com.example.mrmovieapplicationv3.viewmodel.MovieViewModel

// Must observe any changes happened in the ViewModel.
// It does not request any data only OBSERVE continuously the changes in the ViewModel,
// then it get the data when the changes occurs.
class HomePageFragment : Fragment(), MovieAdapter.OnMovieItemClickListener {
    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!
    private val TAG = "HOME_PAGE_FRAGMENT"
    private lateinit var movieViewModel: MovieViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        initializing()

        return binding.root
    }

    private fun initializing() {
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.getMovies().observe(viewLifecycleOwner) {
            setupAdapter(it)
        }
    }

    private fun setupAdapter(movies: List<Movie>) {
        binding.recycleViewId.adapter = MovieAdapter(requireContext(), movies, this)
        binding.recycleViewId.layoutManager = LinearLayoutManager(requireContext())
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