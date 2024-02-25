package com.example.mrmovieapplicationv3.ui.details

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
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
//        val movie: Movie? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            intent.getParcelableExtra(GlobalKeys.MOVIE_DATA, Movie::class.java)
//        } else {
//            intent.getParcelableExtra(GlobalKeys.MOVIE_DATA)
//        }
//
//        val id = movie?.movieID ?: return
//
//        binding.apply {
//            movieNameDetailActId.text = movie.movieName
//            backgroundImageId.setImageResource(movie.moviePoster)
//            lengthDataId.text = movie.movieLength
//            descDataId.apply {
//                text = movie.movieDescription
//                movementMethod = ScrollingMovementMethod()
//            }
//            movieRatingDataId.text = movie.movieRating
//            movieGenreId.text = movie.movieGenre
//            backButtonImageId.setOnClickListener {
//                onBackBtnPressed()
//            }
//            saveImageId.setOnClickListener {
//                if (MovieSharedPreference.isMovieBookmarked(this@DetailActivity, id))
//                {
//                    MovieSharedPreference.unBookmarkThisMovie(this@DetailActivity, id)
//                    saveImageId.setImageResource(R.drawable.unbookmark)
//                }
//                else
//                {
//                    MovieSharedPreference.bookmarkThisMovie(this@DetailActivity, id)
//                    saveImageId.setImageResource(R.drawable.bookmark)
//                }
//                broadcastBookmarkedIntent(movie)
//            }
//        }
    }

//    private fun onBackBtnPressed() {
//        finish()
//    }

//    private fun broadcastBookmarkedIntent(movie: Movie)
//    {
//        val bookmarkedIntent = Intent()
//        bookmarkedIntent.action = GlobalKeys.BROADCAST_ACTION
////        bookmarkedIntent.putExtra(GlobalKeys.MOVIE_DATA, movie) // send object
////        println("Broadcast message has been sent movie booked/unbooked is ${movie.movieName}")
//        this@DetailActivity.sendBroadcast(bookmarkedIntent)
//    }
}