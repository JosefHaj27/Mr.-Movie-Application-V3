package com.example.mrmovieapplicationv3.ui.details

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mrmovieapplicationv3.databinding.ActivityDetailBinding
import com.example.mrmovieapplicationv3.model.movie.Movie
import utils.GlobalKeys


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

        binding.apply {
            movie?.let {
                movieNameDetailActId.text = it.show.name
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

    private fun loadMoviesLanguage(movie: Movie): String {
        val language = movie.show.language
        if (language == null) {
            return " "
        } else {
            return language
        }
    }

    private fun loadMoviesDesc(movie: Movie) {
        binding.descDataId.apply {
            val descText = movie.show.summary
            text = Html.fromHtml(descText, 0).toString()
            movementMethod = ScrollingMovementMethod()
        }
    }

    private fun loadMoviesPoster(movie: Movie) {
        val moviePosterOriginal = movie.show.image?.original
        val moviePosterMedium = movie.show.image?.medium
        val bindBackgroundImage = binding.backgroundImageId

        if (moviePosterOriginal != null) {
            Glide.with(this).load(moviePosterOriginal).into(bindBackgroundImage)
        } else if (moviePosterMedium != null) {
            Glide.with(this).load(moviePosterMedium).into(bindBackgroundImage)
        } else {
            Glide.with(this).load(GlobalKeys.NO_IMAGE_URL).into(bindBackgroundImage)
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

    private fun loadMoviesDuration(movie: Movie): String {
        val durationOfMovie = movie.show.runtime
        var displayedDuration = " "
        var minutes = 0
        var hours = 0
        if (durationOfMovie == null) {
            binding.languageDataId.visibility = View.INVISIBLE
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

    private fun onBackBtnPressed() {
        finish()
    }
}