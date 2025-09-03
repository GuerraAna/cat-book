package com.example.catbook.catsList.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.catbook.catsList.data.model.CatsRequestData
import com.example.catbook.catsList.data.model.CatsResponse
import com.example.catbook.catsList.domain.repository.CatsRepositoryImpl
import retrofit2.Response

internal class CatsPagingSource(
    private val repository: CatsRepositoryImpl
) : PagingSource<Int, CatsResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatsResponse> {
        return try {
            val currentPage = params.key ?: 0
            val requestData = CatsRequestData(page = currentPage)
            val response = repository.getCatList(requestData)
            val responseCode = response.code().toString()

            when {
                response.isSuccessful -> onSuccess(response, currentPage)
                else -> onError(responseCode)
            }
        } catch (exception: Exception) {
            onError(exception.message ?: " Unknown Error")
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CatsResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private fun onSuccess(
        response: Response<List<CatsResponse>>,
        currentPage: Int
    ): LoadResult.Page<Int, CatsResponse> {
        val cats = response.body() ?: emptyList()
        val prevKey = if (currentPage == 0) null else currentPage - 1
        val nextKey = if (cats.isNotEmpty()) currentPage + 1 else null

        return LoadResult.Page(
            data = cats,
            prevKey = prevKey,
            nextKey = nextKey
        )
    }

    private fun onError(message: String): LoadResult.Error<Int, CatsResponse> {
        val errorMessage = "Erro na resposta da API: $message"
        return LoadResult.Error(Exception(errorMessage))
    }
}
