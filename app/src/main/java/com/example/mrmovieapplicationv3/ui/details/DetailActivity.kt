package com.example.mrmovieapplicationv3.ui.details

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.example.mrmovieapplicationv3.databinding.ActivityDetailBinding
import com.example.mrmovieapplicationv3.model.movie.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import utils.MovieSharedPreference
import java.lang.reflect.Type


class DetailActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializing()
    }

    private fun initializing()
    {
        binding.apply {
            movieNameDetailActId.text = intent.getStringExtra("m_name")
            backgroundImageId.setImageResource(intent.getIntExtra("m_img", 0))
            lengthDataId.text = intent.getStringExtra("m_len")
            descDataId.text = intent.getStringExtra("m_des")
            movieRatingDataId.text = intent.getStringExtra("m_rating")
            movieGenreId.text = intent.getStringExtra("m_gen")
            descDataId.movementMethod = ScrollingMovementMethod()
            backButtonImageId.setOnClickListener {
                onBackBtnPressed()
            }
            // here handle listener in  case movie is bookmarked
            saveImageId.setOnClickListener {
                val id = intent.getIntExtra("m_ID", -1)
                val allMovies = MovieSharedPreference.getAllMovies(baseContext)
//                println("not booked yet ${allMovies[id]}")
                if(MovieSharedPreference.isMovieBookmarked(baseContext, id))
                {
                    println("in if statement ${MovieSharedPreference.isMovieBookmarked(baseContext, id)}")
                    MovieSharedPreference.unBookmarkThisMovie(baseContext, id)
                    println("in if statement ${MovieSharedPreference.isMovieBookmarked(baseContext, id)}")
                }
                else
                {
                    println("in else statement ${MovieSharedPreference.isMovieBookmarked(baseContext, id)}")
                    MovieSharedPreference.bookmarkThisMovie(baseContext, id)
                    println("in else statement ${MovieSharedPreference.isMovieBookmarked(baseContext, id)}")
                }
//                val allMovies2 = MovieSharedPreference.getAllMovies(baseContext)
//                println("now booked ${allMovies2[id]}")
            }


        }
    }

    private fun onBackBtnPressed()
    {
        finish()
    }
}