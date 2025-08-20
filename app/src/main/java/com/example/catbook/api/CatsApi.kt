package com.example.catbook.api

import com.example.catbook.model.CatsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface CatsApi {

    /**
     * Get details about cats.
     */
    @GET("v1/images/search")
    suspend fun getCats(
        @Query("limit") limit: Int = 10,
        @Query("page") page: Int = 0,
        @Query("breed_ids") breedIds: String? = null,
        @Query("api_key") apiKey: String = ""
    ): Response<List<CatsResponse>>
}
