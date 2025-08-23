package com.example.catbook.catsList.presentation

import com.example.catbook.catsList.data.model.CatsResponse

internal sealed class CatsState {

    /**
     * Represents the loading when the details about cats are being fetched.
     */
    data object Loading : CatsState()

    /**
     * Represents the success when getting details about cats.
     * @param response List of cats.
     */
    data class Success(val response: List<CatsResponse>) : CatsState()

    /**
     * Represents the error when the details about cats are not retrieved.
     * @param message Error message to explain the error.
     */
    data class Error(val message: String) : CatsState()

    /**
     * Represents the empty state when the list of cats is empty.
     */
    data object Empty : CatsState()
}
