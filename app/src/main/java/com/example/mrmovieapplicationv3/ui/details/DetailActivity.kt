package com.example.mrmovieapplicationv3.ui.details

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mrmovieapplicationv3.R
import com.example.mrmovieapplicationv3.databinding.ActivityDetailBinding
import com.example.mrmovieapplicationv3.model.movie.Movie
import utils.GlobalKeys
import utils.MovieSharedPreference


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializing()
    }

    private fun initializing() {
        val movie: Movie? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(GlobalKeys.MOVIE_DATA, Movie::class.java)
        } else {
            intent.getParcelableExtra(GlobalKeys.MOVIE_DATA)
        }

        val id = movie?.show?.id ?: return

        binding.apply {
            movieNameDetailActId.text = movie.show.name
//            backgroundImageId.setImageResource(movie.show.) // Later using GLIDE
            lengthDataId.text = movie.show.runtime?.toString()
            descDataId.apply {
                text = movie.show.summary
                movementMethod = ScrollingMovementMethod()
        // TODO::remove the html elements from the text
            }
            movieRatingDataId.text = bindRatingData(movie)
            movieGenreId.text = bindGenresData(movie) //movie.show.genres.toString()
            backButtonImageId.setOnClickListener {
                onBackBtnPressed()
            }
        }
    }

    private fun bindRatingData(movie: Movie): String
    {
        return if (movie.show.rating?.average != null) {
            movie.show.rating.average.toString()
        } else {
            0.toString()
        }
    }
    private fun bindGenresData(movie: Movie): String
    {
        val mGenres = movie.show.genres?.size
        if (mGenres != null)
        {
            if (mGenres != 0)
            {
                return movie.show.genres[0]
            }
            else{
                binding.movieGenreId.visibility = View.INVISIBLE
                return ""
            }
        }
        else
        {
            binding.movieGenreId.visibility = View.INVISIBLE
            return ""
        }
        binding.movieGenreId.visibility = View.INVISIBLE
        return ""
    }
    private fun onBackBtnPressed() {
        finish()
    }

//    private fun broadcastBookmarkedIntent(movie: Movie)
//    {
//        val bookmarkedIntent = Intent()
//        bookmarkedIntent.action = GlobalKeys.BROADCAST_ACTION
////        bookmarkedIntent.putExtra(GlobalKeys.MOVIE_DATA, movie) // send object
////        println("Broadcast message has been sent movie booked/unbooked is ${movie.movieName}")
//        this@DetailActivity.sendBroadcast(bookmarkedIntent)
//    }
}