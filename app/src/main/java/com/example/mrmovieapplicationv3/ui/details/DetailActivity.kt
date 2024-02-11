package com.example.mrmovieapplicationv3.ui.details

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.example.mrmovieapplicationv3.databinding.ActivityDetailBinding

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
    }

    private fun onBackBtnPressed()
    {
        finish()
    }
}