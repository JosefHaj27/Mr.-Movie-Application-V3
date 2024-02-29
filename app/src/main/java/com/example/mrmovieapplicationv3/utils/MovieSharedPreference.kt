package com.example.mrmovieapplicationv3.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.mrmovieapplicationv3.model.data.Movie
import com.example.mrmovieapplicationv3.viewmodel.MovieViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MovieSharedPreference {

    companion object {
        const val SHARE_PREF = "com.example.mrmovieapplicationv3.bookmarked_values"
        private const val MOVIES_KEY = "all_movies" // when value changes getAllMovies return null. This key have same the value as in GlobleKeys.ALL_MOVIES why?
        private lateinit var movieViewModel: MovieViewModel
        fun getAllMovies(context: Context): List<Movie> {
            return movieViewModel.getMovies() as List<Movie>
        }

        fun bookmarkThisMovie(context: Context, movieID: Int) {
            val sharePref = context.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE)
            val movies = getAllMovies(context)
            val editor: SharedPreferences.Editor = sharePref.edit()
//            movies[movieID].isBookmarked = true
            val gson = Gson()
            val moviesListJson = gson.toJson(movies)
            editor.putString(MOVIES_KEY, moviesListJson)
            editor.apply()
        }

        fun unBookmarkThisMovie(context: Context, movieID: Int) {
            val sharePref = context.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE)
            val movies = getAllMovies(context)
            val editor: SharedPreferences.Editor = sharePref.edit()
//            movies[movieID].isBookmarked = false
            val gson = Gson()
            val moviesListJson = gson.toJson(movies)
            editor.putString(MOVIES_KEY, moviesListJson)
            editor.apply()
        }

        fun isMovieBookmarked(context: Context, movieID: Int): Boolean {
            val movies = getAllMovies(context)
            return movies[movieID].isBookmarked
        }

//        TODO:: I need these two method. But I don't know yet when nad how.
//              Do I need the listener, if so for what!?
        fun registerMovieSharedPref(
            context: Context,
            listener: SharedPreferences.OnSharedPreferenceChangeListener
        ) {
            val sharePref = context.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE)
            sharePref.registerOnSharedPreferenceChangeListener(listener)
        }

        fun unregisterMovieSharedPref(
            context: Context,
            listener: SharedPreferences.OnSharedPreferenceChangeListener
        ) {
            val sharePref = context.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE)
            sharePref.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }
}