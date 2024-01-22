package com.schaefer.playground.data.local

import android.util.Log
import com.schaefer.playground.core.ApiClient
import com.schaefer.playground.data.model.Venues
import com.schaefer.playground.data.service.VenueService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataSource(
    private val venueService: VenueService = ApiClient.apiService,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    suspend fun getVenue(lat: Double, lng: Double): Result<Venues> {
        return withContext(coroutineDispatcher) {
            try {
                val result = venueService.searchVenue(latLng = "$lat,$lng").response
                Result.success(result)
            } catch (e: Exception) {
                Log.e("RemoteDataSource", "getVenue: ${e}")
                Result.failure(e)
            }
        }
    }
}