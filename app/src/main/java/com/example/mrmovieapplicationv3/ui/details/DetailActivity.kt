package com.example.mrmovieapplicationv3.ui.details

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.example.mrmovieapplicationv3.databinding.ActivityDetailBinding
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
        val id = intent.getIntExtra(GlobalKeys.MOVIE_ID, -1)
        binding.apply {
            movieNameDetailActId.text = intent.getStringExtra(GlobalKeys.MOVIE_NAME)
            backgroundImageId.setImageResource(intent.getIntExtra(GlobalKeys.MOVIE_POSTER, 0))
            lengthDataId.text = intent.getStringExtra(GlobalKeys.MOVIE_LENGTH)
            descDataId.text = intent.getStringExtra(GlobalKeys.MOVIE_DESCRIPTION)
            movieRatingDataId.text = intent.getStringExtra(GlobalKeys.MOVIE_DESCRIPTION)
            movieGenreId.text = intent.getStringExtra(GlobalKeys.MOVIE_ID)
            descDataId.movementMethod = ScrollingMovementMethod()
            backButtonImageId.setOnClickListener {
                onBackBtnPressed()
            }
            saveImageId.setOnClickListener {
                if (MovieSharedPreference.isMovieBookmarked(this@DetailActivity, id)) {
                    MovieSharedPreference.unBookmarkThisMovie(this@DetailActivity, id)
                } else {
                    MovieSharedPreference.bookmarkThisMovie(this@DetailActivity, id)
                }
            }
        }
    }

    private fun onBackBtnPressed() {
        finish()
    }
}