package com.example.catbook.catsList.data

import com.example.catbook.catsList.data.model.CatsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface CatListApi {

    /**
     * Get details about cats to display in the cats list.
     * @param limit Number of cats to retrieve.
     * @param page Page of cats to retrieve.
     * @param apiKey API key to authenticate the request.
     */
    @GET("v1/images/search")
    suspend fun getCatList(
        @Query("limit") limit: Int = 10,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = "live_ZdTzBS3OiNb23XVfi0QaO7rGFj4FLImBh99GERdK05Rn5kOSBRYXQDAZLP6c4iKl"
    ): Response<List<CatsResponse>>
}
