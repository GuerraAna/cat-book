package com.example.catbook

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class CatsRetrofit {

    private val build: Retrofit by lazy { setRetrofitBuilder() }

    private fun setRetrofitBuilder(): Retrofit {
        val jsonFactory = GsonConverterFactory.create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(jsonFactory)
            .build()
    }

    companion object {
        private const val BASE_URL = "https://api.thecatapi.com/"

        /**
         * Get the retrofit instance with the service.
         */
        inline fun <reified T> instance(): T {
            val service = T::class.java
            return CatsRetrofit().build.create(service)
        }
    }
}
