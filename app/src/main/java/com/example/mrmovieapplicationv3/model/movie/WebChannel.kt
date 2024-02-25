package com.example.mrmovieapplicationv3.model.movie

import com.google.gson.annotations.SerializedName

data class WebChannel(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("country")
    val country: String?,

    @SerializedName("officialSite")
    val officialSite: String?,
)
