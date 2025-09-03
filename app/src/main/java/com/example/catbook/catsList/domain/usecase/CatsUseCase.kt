package com.example.catbook.catsList.domain.usecase

import androidx.paging.Pager
import com.example.catbook.catsList.data.model.CatsResponse

internal interface CatsUseCase {

    /**
     * Get the cats pager to retrieves the current cats list.
     */
    fun getCatsPager(): Pager<Int, CatsResponse>
}
