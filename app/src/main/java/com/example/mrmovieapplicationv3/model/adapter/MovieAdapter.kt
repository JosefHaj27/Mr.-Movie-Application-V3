package com.example.mrmovieapplicationv3.model.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mrmovieapplicationv3.databinding.OneRowLayoutBinding
import com.example.mrmovieapplicationv3.model.data.Show
import com.example.mrmovieapplicationv3.utils.GlobalKeys

class MovieAdapter(
    private val context: Context,
    private val movieItems: List<Show>,
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

        fun bind(movie: Show) {
            binding.apply {
                movieNameId.text = movie.name
                movieLengthId.text = loadMoviesDuration(movie)
                movieRatingDataId.text = bindRatingData(movie)
                movieGenreId.text = bindGenresData(movie)
                loadMoviesPoster(movie)
            }
        }

        private fun bindRatingData(movie: Show): String {
            return if (movie.rating?.average != null) {
                movie.rating.average.toString()
            } else {
                0.toString()
            }
        }

        private fun bindGenresData(movie: Show): String {
            val mGenres = movie.genres?.size
            if (mGenres != null && mGenres != 0) {
                binding.movieGenreId.visibility = View.VISIBLE
                return movie.genres[0]
            } else {
                binding.movieGenreId.visibility = View.INVISIBLE
                return " "
            }
        }

        private fun loadMoviesPoster(movie: Show) {
            val moviePosterOriginal = movie.image?.original
            val moviePosterMedium = movie.image?.medium
            val bindPosterImage = binding.moviePosterId

            if (moviePosterOriginal != null) {
                Glide.with(context).load(moviePosterOriginal).into(bindPosterImage)
            } else if (moviePosterMedium != null) {
                Glide.with(context).load(moviePosterMedium).into(bindPosterImage)
            } else {
                Glide.with(context).load(GlobalKeys.NO_IMAGE_URL).into(bindPosterImage)
            }
        }

        private fun loadMoviesDuration(movie: Show): String {
            val durationOfMovie = movie.runtime
            var displayedDuration = " "
            var minutes = 0
            var hours = 0
            if (durationOfMovie == null) {
                binding.clockImageId.visibility = View.INVISIBLE
                return displayedDuration // or it can be set to unknown
            } else {
                hours = durationOfMovie / 60
                minutes = durationOfMovie % 60
                displayedDuration = hours.toString() + "h" + " " + minutes.toString() + "m"
            }
            return displayedDuration
        }
    }

    interface OnMovieItemClickListener {
        fun onMovieItemClick(movie: Show)
    }
}