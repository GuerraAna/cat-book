package com.example.catbook.catsList.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class CatsResponse(
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("breeds") val breeds: List<Breed>
) : Parcelable
