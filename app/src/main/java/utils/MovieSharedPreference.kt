package utils

import android.content.Context
import android.content.SharedPreferences
import com.example.mrmovieapplicationv3.model.movie.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MovieSharedPreference
{
    companion object
    {
        val SHARE_PREF = "com.example.mrmovieapplicationv3.bookmarked_values"
        val MOVIES_KEY = "all_movies"
        val BOOKMARKED_KEY = "bookmarked_movie"

        fun getAllMovies(context: Context): List<Movie>
        {
            val sharePref = context.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE)
            val movies = sharePref.getString(MOVIES_KEY, null)
            val gson = Gson()
            val type: Type = object : TypeToken<List<Movie?>?>() {}.type
            val allMovies = gson.fromJson<List<Movie>>(movies, type)

            return allMovies
        }

        fun bookmarkThisMovie(context: Context, movieID: Int)
        {
            val sharePref = context.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE)
            val movies = getAllMovies(context)
            val editor: SharedPreferences.Editor = sharePref.edit()
            movies[movieID].isBookmarked = true
            val gson = Gson()
            val moviesListJson = gson.toJson(movies)
//            println("before put in MovieSharedPreference.bookmarkThisMovie method: $newMoviesList")
            editor.putString(MOVIES_KEY, moviesListJson)
            editor.apply()
//            println("after put and apply in MovieSharedPreference.bookmarkThisMovie method: $newMoviesList")
        }

        fun unBookmarkThisMovie(context: Context, movieID: Int)
        {
            val sharePref = context.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE)
            val movies = getAllMovies(context)
            val editor: SharedPreferences.Editor = sharePref.edit()
            movies[movieID].isBookmarked = false
            val gson = Gson()
            val moviesListJson = gson.toJson(movies)
//            println("before put in MovieSharedPreference.unBookmarkThisMovie method: $newMoviesList")
            editor.putString(MOVIES_KEY, moviesListJson)
            editor.apply()
//            println("after put and apply in MovieSharedPreference.unBookmarkThisMovie method: $newMoviesList")
        }
        fun unBookmarkAllMovies(context: Context)
        {
            val sharedPreference = context.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreference.edit()
            val movies = getAllMovies(context)
            val unBookmarkedAllMovies = mutableListOf<Movie>()
            val gson = Gson()
            val type: Type = object: TypeToken<List<Movie?>?>(){}.type
            for (movie in movies)
            {
                movie.isBookmarked = false
                unBookmarkedAllMovies.add(movie)
            }

            val allUnBookmarkedJson = gson.toJson(unBookmarkedAllMovies, type)
            editor.putString(MOVIES_KEY, allUnBookmarkedJson)
            editor.apply()
        }

        fun isMovieBookmarked(context: Context, movieID: Int): Boolean
        {
            val movies = getAllMovies(context)
            return movies[movieID].isBookmarked
        }

        fun registerMovieSharedPref(context: Context, listener: SharedPreferences.OnSharedPreferenceChangeListener)
        {
            val sharePref = context.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE)
            sharePref.registerOnSharedPreferenceChangeListener(listener)
        }

        fun unregisterMovieSharedPref(context: Context, listener: SharedPreferences.OnSharedPreferenceChangeListener)
        {
            val sharePref = context.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE)
            sharePref.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }
}