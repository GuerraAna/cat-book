package com.example.catbook.catsList.domain.repository

import com.example.catbook.catsList.data.model.CatsRequestData
import com.example.catbook.catsList.data.model.CatsResponse
import retrofit2.Response

internal interface CatsRepository {

    /**
     * Get details about cats to display in the cats list.
     * @param requestData Data to retrieve fields to get cat list.
     * @return Response with list of cats as body.
     */
    suspend fun getCatList(requestData: CatsRequestData): Response<List<CatsResponse>>
}
