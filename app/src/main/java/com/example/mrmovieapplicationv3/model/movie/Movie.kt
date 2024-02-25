package com.example.mrmovieapplicationv3.model.movie

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import com.example.mrmovieapplicationv3.R
import com.google.gson.annotations.SerializedName

data class Movie (
// any null value in the API, treated as String value.
    @SerializedName("score")
    val score: Double,

    @SerializedName("show")
    val show: Show,


//  TODO:: NO need for these, but when they are commented the constructor throws an error.

//    val movieID: Int,
//
//    val movieName: String?,
//
//    val movieDescription: String?,
//
//    val movieRating: String?,
//
//    val movieGenre: String?,
//
//    val movieLength: String?,
//
//    val moviePoster: Int,
//
//    var isBookmarked: Boolean // by default its false. // Removed
)//: Parcelable
{
    //com.example.mrmovieapplicationv3.model.movie.Movie
    //com.example.mrmovieapplicationv3.model.movie.Movie
/*    constructor(parcel: Parcel) : this(
*//*        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte()*//*
    ) {
    }*/

//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeInt(movieID)
//        parcel.writeString(movieName)
//        parcel.writeString(movieDescription)
//        parcel.writeString(movieRating)
//        parcel.writeString(movieGenre)
//        parcel.writeString(movieLength)
//        parcel.writeInt(moviePoster)
//        parcel.writeByte(if (isBookmarked) 1 else 0)
//    }

//    override fun describeContents(): Int {
//        return 0
//    }

//    companion object CREATOR : Parcelable.Creator<Movie> {
//        override fun createFromParcel(parcel: Parcel): Movie {
//            return Movie("")
//        }
//
//        override fun newArray(size: Int): Array<Movie?> {
//            return arrayOfNulls(size)
//        }

        fun initializeAllLists(context: Context): List<Movie>
        {
            val names = context.resources.getStringArray(R.array.movie_names).toList()
            val desc = context.resources.getStringArray(R.array.movie_descriptions).toList()
            val rating = context.resources.getStringArray(R.array.movie_rating).toList()
            val genre =  context.resources.getStringArray(R.array.movie_genre).toList()
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
//    }
}