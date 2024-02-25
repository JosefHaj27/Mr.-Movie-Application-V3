package com.example.mrmovieapplicationv3.model.movie

import com.google.gson.annotations.SerializedName

data class _Links(

    @SerializedName("self")
    val self: Self?,

    @SerializedName("previousepisode")
    val previousepisode: PreviousEpisode?
)
