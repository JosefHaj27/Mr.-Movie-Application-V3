package com.example.mrmovieapplicationv3.model.movie

import android.content.Context
import android.os.Parcelable
import com.example.mrmovieapplicationv3.R
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("show")
    val show: Show,
) : Parcelable {
    fun initializeAllLists(context: Context): List<Movie> {
        val names = context.resources.getStringArray(R.array.movie_names).toList()
        val desc = context.resources.getStringArray(R.array.movie_descriptions).toList()
        val rating = context.resources.getStringArray(R.array.movie_rating).toList()
        val genre = context.resources.getStringArray(R.array.movie_genre).toList()
        val length = context.resources.getStringArray(R.array.movie_length).toList()
        val images = allImages()

        val movies = mutableListOf<Movie>()
        for (i in 0..4) // number of movies exists is 5.
        {
//                movies.add(Movie(i, names[i], desc[i], rating[i], genre[i], length[i], images[i], false))
        }
        return movies
    }

    private fun allImages(): List<Int> {
        return listOf(
            R.drawable.avengers,
            R.drawable.interstellar,
            R.drawable.matrix,
            R.drawable.leon_the_professional,
            R.drawable.the_godfather,
        )
    }
}