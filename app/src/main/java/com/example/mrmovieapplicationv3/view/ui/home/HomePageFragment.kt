package com.example.mrmovieapplicationv3.view.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mrmovieapplicationv3.databinding.FragmentHomePageBinding
import com.example.mrmovieapplicationv3.model.adapter.MovieAdapter
import com.example.mrmovieapplicationv3.model.data.Show
import com.example.mrmovieapplicationv3.utils.GlobalKeys
import com.example.mrmovieapplicationv3.view.ui.details.DetailActivity
import com.example.mrmovieapplicationv3.viewmodel.MovieViewModel
import java.util.Timer
import kotlin.concurrent.schedule

class HomePageFragment : Fragment(), MovieAdapter.OnMovieItemClickListener {
    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel: MovieViewModel by viewModels()
    private var pageNumber = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        initializing()

        return binding.root
    }

    private fun initializing() {
        movieViewModel.callingAPIForMoviesData()
        movieViewModel.getMovies().observe(viewLifecycleOwner) {
            setupAdapter(it)
            refreshListener(it)
            searchViewListener()
        }
    }

    private fun searchViewListener() {
        binding.searchViewId.apply {
            queryHint = "Search..."
            if (isIconified) {
                setOnQueryTextListener(object : OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        movieViewModel.callingAPIForMoviesData(query)
                        onActionViewCollapsed()
                        setQuery(query, false)
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return true
                    }
                })
            } else {
                setOnQueryTextListener(object : OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        movieViewModel.callingAPIForShowsPagesData(pageNumber)
                        onActionViewCollapsed()
                        setQuery(query, false)
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return true
                    }
                })
            }
        }
    }

    private fun setupAdapter(movies: List<Show>) {
        binding.recycleViewId.apply {
            adapter = MovieAdapter(requireContext(), movies, this@HomePageFragment)
            layoutManager = LinearLayoutManager(requireContext())

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(this@apply, newState)
                    if (!recyclerView.canScrollVertically(1) && scrollState == RecyclerView.SCROLL_STATE_IDLE) {
                        pageNumber++
                        movieViewModel.callingAPIForShowsPagesData(pageNumber)
//                  TODO:: BUG:: when the end reached, it refresh the page and start from the top of the page.
                    }
                }
            })
            adapter?.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMovieItemClick(movie: Show) {
        val myIntent = Intent(requireContext(), DetailActivity::class.java)
        myIntent.putExtra(GlobalKeys.MOVIE_DATA, movie)
        startActivity(myIntent)
    }

    private fun refreshListener(movies: List<Show>) {
        binding.apply {
            swipeToRefreshId.setOnRefreshListener {
                movieViewModel.shuffleMovies(movies)
                recycleViewId.adapter?.notifyDataSetChanged()
                Timer().schedule(1000) { swipeToRefreshId.isRefreshing = false }
            }
        }
    }
}