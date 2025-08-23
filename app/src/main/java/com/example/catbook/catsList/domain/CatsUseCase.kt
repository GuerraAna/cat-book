package com.example.catbook.catsList.domain

import com.example.catbook.catsList.data.model.CatsResponse
import retrofit2.Response

internal class CatsUseCase(
    private val repository: CatsRepository = CatsRepository()
) {

    /**
     * Get details about cats to display in the cats list.
     * @return Response with list of cats as body.
     */
    suspend fun getCatList(): Response<List<CatsResponse>> = repository.getCatList()
}
