package com.example.mrmovieapplicationv3.model.movie

import android.content.Context
import com.example.mrmovieapplicationv3.R

data class Movie (
    val movieName: String,
    val movieDescription: String,
    val movieRating: String,
    val movieGenre: String,
    val movieLength: String,
    val moviePoster: Int,
    var isBookmarked: Boolean // by default its false.
)
{
    companion object{
        fun initializeAllLists(context: Context): List<Movie>
        {
            val names = context.resources.getStringArray(R.array.movie_names).toList()
            val desc = context.resources.getStringArray(R.array.movie_descriptions).toList()
            val rating = context.resources.getStringArray(R.array.movie_rating).toList()
            val genre =  context.resources.getStringArray(R.array.movie_genre).toList()
            val length = context.resources.getStringArray(R.array.movie_length).toList()
            val images = allImages()
            val bookmarkedMovieList = mutableListOf<Boolean>()

            val movies = mutableListOf<Movie>()
            for (i in 0..4) // number of movies exists
            {
                movies.add(Movie(names[i], desc[i], rating[i], genre[i], length[i], images[i], false))
            }
            movies[0].isBookmarked = true
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
}