package com.example.mrmovieapplicationv3.model.movie

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("show")
    val show: Show,
) : Parcelable