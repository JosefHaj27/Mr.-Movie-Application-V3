package com.example.mrmovieapplicationv3.model.movie

import com.google.gson.annotations.SerializedName

data class External(
    @SerializedName("tvrage")
    val tvrage: String?,

    @SerializedName("thetvdb")
    val thetvdb: String?,

    @SerializedName("imdb")
    val imdb: String?
)
