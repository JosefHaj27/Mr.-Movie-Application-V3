package com.example.mrmovieapplicationv3.ui.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mrmovieapplicationv3.databinding.OneRowLayoutBinding
import com.example.mrmovieapplicationv3.model.movie.Movie
import utils.GlobalKeys

class MovieAdapter(
    private val context: Context,
    private val movieItems: List<Movie>,
    private val listener: OnMovieItemClickListener?
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = OneRowLayoutBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieItems.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieItems[position])
    }

    inner class MovieViewHolder(private val binding: OneRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.topCardViewId.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val movie = movieItems[position]
                    listener?.onMovieItemClick(movie)
                }
            }
        }

        fun bind(movie: Movie) {
            binding.apply {
                movieNameId.text = movie.show.name
                movieLengthId.text = loadMoviesDuration(movie)
                movieRatingDataId.text = bindRatingData(movie)
                movieGenreId.text = bindGenresData(movie)
                loadMoviesPoster(movie)
            }
        }

        private fun bindRatingData(movie: Movie): String {
            return if (movie.show.rating?.average != null) {
                movie.show.rating.average.toString()
            } else {
                0.toString()
            }
        }

        private fun bindGenresData(movie: Movie): String {
            val mGenres = movie.show.genres?.size
            if (mGenres != null && mGenres != 0) {
                binding.movieGenreId.visibility = View.VISIBLE
                return movie.show.genres[0]
            } else {
                binding.movieGenreId.visibility = View.INVISIBLE
                return " "
            }
        }

        private fun loadMoviesPoster(movie: Movie) {
            val moviePosterOriginal = movie.show.image?.original
            val moviePosterMedium = movie.show.image?.medium
            val bindPosterImage = binding.moviePosterId

            if (moviePosterOriginal != null) {
                Glide.with(context).load(moviePosterOriginal).into(bindPosterImage)
            } else if (moviePosterMedium != null) {
                Glide.with(context).load(moviePosterMedium).into(bindPosterImage)
            } else {
                Glide.with(context).load(GlobalKeys.NO_IMAGE_URL).into(bindPosterImage)
            }
        }

        private fun loadMoviesDuration(movie: Movie): String {
            val durationOfMovie = movie.show.runtime
            var displayedDuration = " "
            var minutes = 0
            var hours = 0
            if (durationOfMovie == null) {
                binding.clockImageId.visibility = View.INVISIBLE
                return displayedDuration // or it can be set to unknown
            } else {
                if (durationOfMovie % 60 == 0) {
                    hours = durationOfMovie / 60
                    minutes = durationOfMovie % 60
                    displayedDuration = hours.toString() + "h" + " " + minutes.toString() + "m"
                } else {
                    hours = 0
                    minutes = durationOfMovie
                    displayedDuration = hours.toString() + "h" + " " + minutes.toString() + "m"
                }
            }
            return displayedDuration
        }
    }

    interface OnMovieItemClickListener {
        fun onMovieItemClick(movie: Movie)
    }
}