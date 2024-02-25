package com.example.mrmovieapplicationv3.model.movie

import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("average")
    val average: Double?
)
