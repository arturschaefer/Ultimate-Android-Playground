package com.schaefer.playground.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.schaefer.playground.data.repository.VenueRepositoryDefault
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel(
    // TODO use the domain interface instead data directly
    private val repository: VenueRepositoryDefault
) : ViewModel() {

    private var _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state: StateFlow<HomeState> = _state

    fun searchVenues(lat: Double, lng: Double) {
        repository.getVenue(lat, lng).onEach {
            _state.value = HomeState.DataLoaded(it)
        }.launchIn(viewModelScope)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository = VenueRepositoryDefault()
                HomeViewModel(
                    repository = repository
                )
            }
        }
    }
}