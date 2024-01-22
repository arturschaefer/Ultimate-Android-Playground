package com.schaefer.playground.ui.home

import com.schaefer.playground.domain.model.Venue

sealed class HomeState(
    val isLoading: Boolean = true,
    val hasError: Boolean = false,
) {
    data class DataLoaded(val venues: List<Venue> = emptyList()) :
        HomeState(isLoading = false, hasError = false)

    data class Error(val message: String) : HomeState(hasError = true, isLoading = false)

    data object Loading : HomeState(isLoading = true, hasError = false)

}
