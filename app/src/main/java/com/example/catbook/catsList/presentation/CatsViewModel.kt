package com.example.catbook.catsList.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.catbook.catsList.data.model.CatsResponse
import com.example.catbook.catsList.domain.usecase.CatsUseCaseImpl
import kotlinx.coroutines.flow.Flow

internal class CatsViewModel @JvmOverloads constructor(
    application: Application,
    useCase: CatsUseCaseImpl = CatsUseCaseImpl()
) : AndroidViewModel(application) {

    /**
     * Represents the current state of the cats list.
     */
    val catsPagingData: Flow<PagingData<CatsResponse>> = getCatsFlow(useCase)

    private fun getCatsFlow(useCase: CatsUseCaseImpl) = useCase.getCatsPager().flow.cachedIn(
        scope = viewModelScope
    )
}
