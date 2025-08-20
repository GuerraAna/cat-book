package com.example.catbook.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object RetrofitInstance {
    private const val BASE_URL = "https://api.thecatapi.com/"

    /**
     *
     */
    val api: CatsApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatsApi::class.java)
    }
}