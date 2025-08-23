package com.example.catbook.catsList.domain

import com.example.catbook.CatsRetrofit
import com.example.catbook.catsList.data.CatListApi
import com.example.catbook.catsList.data.model.CatsResponse
import retrofit2.Response

internal class CatsRepository(
    private val service: CatListApi = CatsRetrofit.instance<CatListApi>()
) {

    /**
     * Get details about cats to display in the cats list.
     * @return Response with list of cats as body.
     */
    suspend fun getCatList(): Response<List<CatsResponse>> = service.getCatList()
}
