package com.example.mrmovieapplicationv3.model.movie

import com.google.gson.annotations.SerializedName

data class Show(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("type")
    val type: String?,

    @SerializedName("language")
    val language: String?,

    @SerializedName("genres")
    val genres: List<String>?,   // this may be wrong check on it if something break.
    // should it be  List<String?>? or  List<String?>

    @SerializedName("status")
    val status: String?,

    @SerializedName("runtime")
    val runtime: String?,

    @SerializedName("averageRuntime")
    val averageRuntime: Int?,

    @SerializedName("premiered")
    val premiered: String?, // need to be handled as date.

    @SerializedName("ended")
    val ended: String?,

    @SerializedName("officialSite")
    val officialSite: String?,

    @SerializedName("schedule")
    val schedule: Schedule?,

    @SerializedName("rating")
    val rating: Rating?,

    @SerializedName("weight")
    val weight: Int?,

//    @SerializedName("network")
//    val network: Any?,

    @SerializedName("webChannel")
    val webChannel: WebChannel?,

    @SerializedName("dvdCountry")
    val dvdCountry: String?,

    @SerializedName("externals")
    val externals: External?,

    @SerializedName("image")
    val image: Image?,

    @SerializedName("summary")
    val summary: String?,

    @SerializedName("updated")
    val updated: String?,

    @SerializedName("_links")
    val _links: _Links?
)