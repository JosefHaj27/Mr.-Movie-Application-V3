package com.example.mrmovieapplicationv3

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mrmovieapplicationv3.databinding.OneRowLayoutBinding

class MyAdapter(
    private val context: Context,
    private val movieItems: List<MovieData>
): RecyclerView.Adapter<MyAdapter.MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        val view = OneRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int
    {
        return movieItems.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        // not correct!
        val currentItem = movieItems[position]
        holder.apply {
            nameTV.text = currentItem.movieName
            genreTV.text = currentItem.movieGenre
            ratingTV.text = currentItem.movieRating
            posterImage.setImageResource(currentItem.moviePoster)
            lengthTV.text = currentItem.movieLength
        }

        // action listener of the one row layout
        holder.constraintLayout.setOnClickListener{
            val myIntent = Intent(context, DetailActivity::class.java)
            myIntent.apply {
                putExtra("m_name", currentItem.movieName)
                putExtra("m_genre", currentItem.movieGenre)
                putExtra("m_rating", currentItem.movieRating)
                putExtra("m_img", currentItem.moviePoster)
                putExtra("m_len", currentItem.movieLength)
                putExtra("m_des", currentItem.movieDescription)
            }
            context.startActivity(myIntent)
        }
    }

    inner class MyViewHolder(binding: OneRowLayoutBinding): RecyclerView.ViewHolder(binding.root)
    {
        // not correct!
        val posterImage = binding.moviePosterId
        val nameTV = binding.movieNameId
        val genreTV = binding.movieGenreDataDetailActId
        val ratingTV = binding.movieRatingDataId
        val lengthTV = binding.movieLengthId
        val constraintLayout = binding.oneRowLayoutId
    }
}