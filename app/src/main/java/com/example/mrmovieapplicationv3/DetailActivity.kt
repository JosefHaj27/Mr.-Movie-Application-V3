package com.example.mrmovieapplicationv3

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.ImageView
import android.widget.Toast
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
        goBack()
    }

    private fun initializing()
    {
        binding.movieNameDetailActId.text = intent.getStringExtra("m_name")
        binding.backgroundImageId.setImageResource(intent.getIntExtra("m_img", 0))
        binding.lengthDataId.text = intent.getStringExtra("m_len")
        binding.descDataId.text = intent.getStringExtra("m_des")
        binding.movieRatingDataId.text = intent.getStringExtra("m_rating")
        binding.movieGenreDataDetailActId.text = intent.getStringExtra("m_genre")

        binding.descDataId.movementMethod = ScrollingMovementMethod()
    }

    private fun goBack()
    {
        binding.backButtonImageId.setOnClickListener {
            finish()
        }
    }
}