package com.example.mrmovieapplicationv3.view.ui.details

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mrmovieapplicationv3.databinding.ActivityDetailBinding
import com.example.mrmovieapplicationv3.model.data.Show
import com.example.mrmovieapplicationv3.utils.GlobalKeys


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializing()
    }

    private fun initializing() {
        val movie: Show? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(GlobalKeys.MOVIE_DATA, Show::class.java)
        } else {
            intent.getParcelableExtra(GlobalKeys.MOVIE_DATA)
        }

        binding.apply {
            movie?.let {
                movieNameDetailActId.text = it.name
                loadMoviesPoster(it)
                lengthDataId.text = loadMoviesDuration(it)
                loadMoviesDesc(it)
                movieRatingDataId.text = bindRatingData(it)
                movieGenreId.text = bindGenresData(it)
                languageDataId.text = loadMoviesLanguage(it)
            }
            backButtonImageId.setOnClickListener {
                onBackBtnPressed()
            }
        }
    }

    private fun loadMoviesLanguage(movie: Show): String {
        val language = movie.language
        if (language == null) {
            return " "
        } else {
            return language
        }
    }

    private fun loadMoviesDesc(movie: Show) {
        binding.descDataId.apply {
            val descText = movie.summary
            text = Html.fromHtml(descText, 0).toString()
            movementMethod = ScrollingMovementMethod()
        }
    }

    private fun loadMoviesPoster(movie: Show) {
        val moviePosterOriginal = movie.image?.original
        val moviePosterMedium = movie.image?.medium
        val bindBackgroundImage = binding.backgroundImageId

        if (moviePosterOriginal != null) {
            Glide.with(this).load(moviePosterOriginal).into(bindBackgroundImage)
        } else if (moviePosterMedium != null) {
            Glide.with(this).load(moviePosterMedium).into(bindBackgroundImage)
        } else {
            Glide.with(this).load(GlobalKeys.NO_IMAGE_URL).into(bindBackgroundImage)
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

    private fun loadMoviesDuration(movie: Show): String {
        val durationOfMovie = movie.runtime
        var displayedDuration = " "
        var minutes = 0
        var hours = 0
        if (durationOfMovie == null) {
            binding.languageDataId.visibility = View.INVISIBLE
            return displayedDuration
        } else {
            hours = durationOfMovie / 60
            minutes = durationOfMovie % 60
            displayedDuration = hours.toString() + "h" + " " + minutes.toString() + "m"
        }
        return displayedDuration
    }

    private fun onBackBtnPressed() {
        finish()
    }
}