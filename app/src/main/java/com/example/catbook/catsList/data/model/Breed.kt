package com.example.catbook.catsList.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Breed(
    @SerializedName("weight") val weight: List<Weight>,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("cfa_url") val cfaUrl: String?,
    @SerializedName("vetstreet_url") val vetstreetUrl: String?,
    @SerializedName("vcahospitals_url") val vcahospitalsUrl: String?,
    @SerializedName("temperament") val temperament: String?,
    @SerializedName("origin") val origin: String?,
    @SerializedName("country_codes") val countryCodes: String?,
    @SerializedName("country_code") val countryCode: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("life_span") val lifeSpan: String?,
    @SerializedName("indoor") val indoor: Int?,
    @SerializedName("lap") val lap: Int?,
    @SerializedName("wikipedia_url") val wikipediaUrl: String?,
    @SerializedName("hypoallergenic") val hypoallergenic: Int?, // Ou Boolean, dependendo de como você quer tratar
    @SerializedName("reference_image_id") val referenceImageId: String?
) : Parcelable
