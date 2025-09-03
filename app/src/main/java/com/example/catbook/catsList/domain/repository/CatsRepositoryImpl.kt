package com.example.catbook.catsList.domain.repository

import com.example.catbook.CatsRetrofit
import com.example.catbook.catsList.data.CatListApi
import com.example.catbook.catsList.data.model.CatsRequestData
import com.example.catbook.catsList.data.model.CatsResponse
import retrofit2.Response

internal class CatsRepositoryImpl(
    private val service: CatListApi = CatsRetrofit.instance<CatListApi>()
) : CatsRepository {

    /**
     * Get details about cats to display in the cats list.
     * @param requestData Data to retrieve fields to get cat list.
     * @return Response with list of cats as body.
     */
    override suspend fun getCatList(requestData: CatsRequestData): Response<List<CatsResponse>> =
        service.getCatList(page = requestData.page, limit = requestData.limit)
}
