package com.example.catbook

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.catbook.api.CatsApi
import com.example.catbook.api.RetrofitInstance
import com.example.catbook.model.CatsResponse
import kotlinx.coroutines.launch
import retrofit2.Response

internal class CatsRepository(
    private val catsApi: CatsApi = RetrofitInstance.api
) {

    /**
     * Get details about cats.
     */
    suspend fun getCats(): Response<List<CatsResponse>> = catsApi.getCats()
}
