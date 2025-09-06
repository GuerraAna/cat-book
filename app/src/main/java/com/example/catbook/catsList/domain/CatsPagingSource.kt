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
            onError(exception.message ?: UNKNOWN_ERROR)
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

        return LoadResult.Page(
            data = cats,
            prevKey = getPrevPage(currentPage),
            nextKey = getNextPage(cats, currentPage)
        )
    }

    private fun getPrevPage(currentPage: Int): Int? {
        return if (currentPage != 0) currentPage - 1 else null
    }

    private fun getNextPage(cats: List<CatsResponse>, currentPage: Int): Int? {
        return if (cats.isNotEmpty()) currentPage + 1 else null
    }

    private fun onError(message: String): LoadResult.Error<Int, CatsResponse> {
        return LoadResult.Error(throwable = Exception(message))
    }

    companion object {
        private const val UNKNOWN_ERROR = "Unknown error"
    }
}
