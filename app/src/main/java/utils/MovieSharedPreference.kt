package utils

import android.content.Context
import android.content.SharedPreferences

class MovieSharedPreference
{
    companion object
    {
        val SHARE_PREF = "com.example.mrmovieapplicationv3.bookmarked_values"
        //val BOOKMARKED_KEY = "bookmarked_movie"
        fun bookmarkThisMovie(context: Context, movieID: Int)
        {
//            val sharePref = context.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE)
//            val editor: SharedPreferences.Editor = sharePref.edit()
//            editor.putBoolean(movieID.toString(), true)
//            editor.apply()
        }

        fun unBookmarkThisMovie(context: Context, movieID: Int)
        {
//            val sharePref = context.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE)
//            val editor: SharedPreferences.Editor = sharePref.edit()
//            editor.putBoolean(movieID.toString(), false)
//            editor.apply()
        }

        fun isMovieBookmarked(context: Context, movieID: Int): Boolean
        {
//            val sharePref = context.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE)
//            return sharePref.getBoolean(movieID.toString(), false)
            return false
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