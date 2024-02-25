package com.example.mrmovieapplicationv3.model.movie

import com.google.gson.annotations.SerializedName

data class Schedule(

    @SerializedName("time")
    val time: String?,

    @SerializedName("days")
    val days: List<String>?, // also, may cause error.

    )
