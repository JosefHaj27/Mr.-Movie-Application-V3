package com.example.mrmovieapplicationv3.model.movie

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rating(
    @SerializedName("average")
    val average: Double?
) : Parcelable
