package com.example.catbook

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class CatsViewModel @JvmOverloads constructor(
    application: Application,
    private val useCase: CatsUseCase = CatsUseCase()
) : AndroidViewModel(application) {

    private val _state = MutableStateFlow(CatsState(isEmpty = true))

    /**
     * Get state of initial details about cats.
     */
    val state: SharedFlow<CatsState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getCats()
        }
    }

    private suspend fun getCats() {
        try {
            val response = useCase.getCats()

            if (response.body().isNullOrEmpty()) {
                _state.value = CatsState(isEmpty = true)
            } else {
                _state.value = CatsState(response = response.body(), isLoading = false)
            }
        } catch (error: Throwable) {
            Log.e("ViewModel", error.message ?: "Unknown error")
            _state.value = CatsState(isError = true, isEmpty = true)
        }
    }
}
