package com.example.mrmovieapplicationv3

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mrmovieapplicationv3.databinding.OneRowLayoutBinding

class MovieAdapter(
    private val context: Context,
    private val movieItems: List<MovieData>,
    private val listener: OnMovieItemClickListener?
): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>()
{
    private lateinit var binding: OneRowLayoutBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder
    {
        val inflater = LayoutInflater.from(context)
        binding = OneRowLayoutBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int
    {
        return movieItems.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int)
    {
        holder.bind(movieItems[position])
    }

    inner class MovieViewHolder(itemView: OneRowLayoutBinding): RecyclerView.ViewHolder(itemView.root)
    {
        init {
            itemView.topCardViewId.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val movie = movieItems[position]
                    listener?.onMovieItemClick(movie)
//                    OnMovieItemClickListener.onMovieItemClick(movie)
                }
            }
        }

            fun bind(movie: MovieData)
            {
                binding.apply {
                    movieNameId.text = movie.movieName
                    movieLengthId.text = movie.movieLength
                    moviePosterId.setImageResource(movie.moviePoster)
                    movieRatingDataId.text = movie.movieRating
                    movieGenreId.text = movie.movieGenre
                }
            }
    }

    interface OnMovieItemClickListener
    {
            fun onMovieItemClick(movie: MovieData){}
    }
}