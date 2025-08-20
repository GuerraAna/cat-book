package com.example.catbook

import android.content.Context
import com.example.catbook.model.CatsResponse
import retrofit2.Response

internal class CatsUseCase(
    private val repository: CatsRepository = CatsRepository()
) {

    /**
     * Get details about cats.
     */
    suspend fun getCats(): Response<List<CatsResponse>> = repository.getCats()
}
