package com.example.mrmovieapplicationv3.ui.details

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.BundleCompat
import androidx.core.content.IntentCompat
import com.example.mrmovieapplicationv3.R
import com.example.mrmovieapplicationv3.databinding.ActivityDetailBinding
import com.example.mrmovieapplicationv3.model.movie.Movie
import com.example.mrmovieapplicationv3.ui.home.HomePageFragment
import utils.BookmarkedBroadcastReceiver
import utils.GlobalKeys
import utils.MovieSharedPreference


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializing()
    }

//    TODO:: Not good, needs more works.
    override fun onResume()
    {
        super.onResume()
        binding.apply {
            val x = intent.getBooleanExtra(GlobalKeys.MOVIE_BOOKMARKED, false)
            if (x)
            {
                saveImageId.setImageResource(R.drawable.home_icon)
            }
            else
            {
                saveImageId.setImageResource(R.drawable.bookmark)
            }
        }

    }
  //  @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun initializing() {
//        val id = intent.getIntExtra(GlobalKeys.MOVIE_ID, -1)

        val movie = intent.getParcelableExtra(GlobalKeys.MOVIE_DATA, Movie::class.java)
        println(movie)

        val id = movie?.movieID!!

        binding.apply {
            movieNameDetailActId.text = movie.movieName
            backgroundImageId.setImageResource(movie.moviePoster)
            lengthDataId.text = movie.movieLength
            descDataId.apply {
                text = movie.movieDescription
                movementMethod = ScrollingMovementMethod()
            }
            movieRatingDataId.text = movie.movieRating
            movieGenreId.text = movie.movieGenre
            backButtonImageId.setOnClickListener {
                onBackBtnPressed()
            }
            saveImageId.setOnClickListener {
                if (MovieSharedPreference.isMovieBookmarked(this@DetailActivity, id))
                {
                    MovieSharedPreference.unBookmarkThisMovie(this@DetailActivity, id)
                    saveImageId.setImageResource(R.drawable.bookmark) // not bookmarked icon
                }
                else
                {
                    MovieSharedPreference.bookmarkThisMovie(this@DetailActivity, id)
                    saveImageId.setImageResource(R.drawable.home_icon) // bookmarked icon
                }
                // send broadcast
//                broadcastBookmarkedIntent()
            }
        }
    }

    private fun onBackBtnPressed() {
        finish()
    }

    private fun broadcastBookmarkedIntent(movie: Movie)
    {
        val bookmarkedIntent = Intent()
        bookmarkedIntent.action = GlobalKeys.BROADCAST_ACTION
        bookmarkedIntent.putExtra(GlobalKeys.MOVIE_DATA, movie) // send object
        this@DetailActivity.sendBroadcast(bookmarkedIntent)
    }
}