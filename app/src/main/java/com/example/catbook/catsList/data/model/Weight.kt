package com.example.catbook.catsList.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weight(
    @SerializedName("imperial") val imperial: String,
    @SerializedName("metric") val metric: String
) : Parcelable
