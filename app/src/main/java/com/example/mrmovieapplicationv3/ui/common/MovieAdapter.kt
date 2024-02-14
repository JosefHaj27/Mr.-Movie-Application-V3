package com.example.mrmovieapplicationv3.ui.common

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mrmovieapplicationv3.databinding.OneRowLayoutBinding
import com.example.mrmovieapplicationv3.model.movie.Movie

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
                movieNameId.text = movie.movieName
                movieLengthId.text = movie.movieLength
                moviePosterId.setImageResource(movie.moviePoster)
                movieRatingDataId.text = movie.movieRating
                movieGenreId.text = movie.movieGenre
            }
        }
    }

    interface OnMovieItemClickListener {
        fun onMovieItemClick(movie: Movie)
    }
}