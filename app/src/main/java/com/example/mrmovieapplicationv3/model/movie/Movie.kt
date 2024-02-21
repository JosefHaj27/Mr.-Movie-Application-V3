package com.example.mrmovieapplicationv3.model.movie

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import com.example.mrmovieapplicationv3.R
import com.google.gson.annotations.SerializedName

data class Movie (
    val movieID: Int,

    @SerializedName("name")
    val movieName: String?,

    @SerializedName("summary")
    val movieDescription: String?,

    @SerializedName("rating")
    val movieRating: String?,

    @SerializedName("genres")
    val movieGenre: String?,

    @SerializedName("status") // Wrong, just for testing
    val movieLength: String?,

    @SerializedName("original")
    val moviePoster: Int,  // needs to be changed to string because it received it as a string value.

    var isBookmarked: Boolean // by default its false.
): Parcelable
{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(movieID)
        parcel.writeString(movieName)
        parcel.writeString(movieDescription)
        parcel.writeString(movieRating)
        parcel.writeString(movieGenre)
        parcel.writeString(movieLength)
        parcel.writeInt(moviePoster)
        parcel.writeByte(if (isBookmarked) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }

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
                movies.add(Movie(i, names[i], desc[i], rating[i], genre[i], length[i], images[i], false))
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
}