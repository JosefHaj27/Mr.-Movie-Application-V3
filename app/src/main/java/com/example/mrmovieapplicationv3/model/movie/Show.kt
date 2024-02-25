package com.example.mrmovieapplicationv3.model.movie

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Show(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("language")
    val language: String?,

    @SerializedName("genres")
    val genres: List<String>?,    // should it be  List<String?>? or  List<String?>

    @SerializedName("runtime")
    val runtime: Int?,

    @SerializedName("rating")
    val rating: Rating?,

    @SerializedName("image")
    val image: Image?,

    @SerializedName("summary")
    val summary: String?
) : Parcelable
