package com.example.catbook.catsList.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.catbook.catsList.data.model.CatsResponse
import com.example.catbook.catsList.domain.CatsPagingSource
import com.example.catbook.catsList.domain.repository.CatsRepositoryImpl

internal class CatsUseCaseImpl(
    private val repository: CatsRepositoryImpl = CatsRepositoryImpl()
) : CatsUseCase {

    /**
     * Get the cats pager to retrieves the current cats list.
     */
    override fun getCatsPager(): Pager<Int, CatsResponse> = Pager(
        config = PagingConfig(
            pageSize = 10,
            prefetchDistance = 9,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { CatsPagingSource(repository) }
    )
}
