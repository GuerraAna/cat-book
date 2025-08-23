package com.example.catbook.catsList.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.catbook.catsList.data.model.CatsResponse
import com.example.catbook.catsList.domain.CatsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class CatsViewModel @JvmOverloads constructor(
    application: Application,
    private val useCase: CatsUseCase = CatsUseCase()
) : AndroidViewModel(application) {

    private val _state = MutableStateFlow<CatsState>(CatsState.Loading)

    /**
     * Get state of initial details about cats.
     */
    val state: SharedFlow<CatsState> = _state.asStateFlow()

    /**
     * Initialize view model and try to load details about cats.
     */
    init {
        viewModelScope.launch { tryGetCatList() }
    }

    private suspend fun tryGetCatList() {
        try {
            val response = useCase.getCatList()
            getCatListState(response.body())
        } catch (error: Throwable) {
            val errorMessage = error.message ?: UNKNOWN_ERROR
            emitState(CatsState.Error(errorMessage))
        }
    }

    private suspend fun getCatListState(catList: List<CatsResponse>?) {
        emitState(
            if (catList.isNullOrEmpty()) CatsState.Empty
            else CatsState.Success(catList)
        )
    }

    private suspend fun emitState(state: CatsState) { _state.emit(state) }

    companion object {
        private const val UNKNOWN_ERROR = "Unknown error"

    }
}
