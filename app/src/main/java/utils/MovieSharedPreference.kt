package utils

import android.content.Context
import android.content.SharedPreferences
import com.example.mrmovieapplicationv3.model.movie.Movie

class MovieSharedPreference
{
    companion object
    {
        private val SHARE_PREF = "com.example.mrmovieapplicationv3.bookmarked_values"
        private val BOOKMARKED_KEY = "bookmarked_movie"
        fun bookmarkThisMovie(context: Context)
        {
            val sharePref = context.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharePref.edit()
            editor.putBoolean(BOOKMARKED_KEY, true)
//        val movies = Movie.initializeAllLists(context)
//        movies[this].isBookmarked = true
        println("bookmarkThisMovie() method in MovieSharedPreference called")
            editor.apply()
        }

        fun unBookmarkThisMovie(context: Context)
        {
            val sharePref = context.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharePref.edit()
            editor.putBoolean(BOOKMARKED_KEY, false)
            editor.apply()
        }

        fun isMovieBookmarked(context: Context): Boolean
        {
            val sharePref = context.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE)
            return sharePref.getBoolean(BOOKMARKED_KEY, false)
        }
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