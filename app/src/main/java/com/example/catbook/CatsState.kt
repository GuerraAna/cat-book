package com.example.catbook

import com.example.catbook.model.CatsResponse

internal data class CatsState(
    val response: List<CatsResponse>? = null,
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isEmpty: Boolean = false
)
