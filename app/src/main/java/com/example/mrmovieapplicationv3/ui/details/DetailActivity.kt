package com.example.mrmovieapplicationv3.ui.details

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mrmovieapplicationv3.databinding.ActivityDetailBinding
import com.example.mrmovieapplicationv3.model.movie.Movie
import com.example.mrmovieapplicationv3.ui.bookmark.BookmarkPageFragment
import utils.MovieSharedPreference

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
        }

        binding.saveImageId.setOnClickListener {
            MovieSharedPreference.bookmarkThisMovie(this)
            println("in detail activity : ${MovieSharedPreference.isMovieBookmarked(this)}")
//            val myIntent = Intent(this, BookmarkPageFragment::class.java)
//            myIntent.putExtra("bookmarked", MovieSharedPreference.isMovieBookmarked(this))

            val myBundle = Bundle()
            myBundle.putBoolean("bookmarked", MovieSharedPreference.isMovieBookmarked(this))
            myBundle.putString("test", "testing value hereeeee")
            val bookmarkPageFragment = BookmarkPageFragment()
            bookmarkPageFragment.arguments = myBundle
        }

    }

    private fun onBackBtnPressed()
    {
        finish()
    }
}