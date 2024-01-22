package com.schaefer.playground.data.service

import com.schaefer.playground.core.RetrofitClient.FOUR_SQUARE_CLIENT
import com.schaefer.playground.core.RetrofitClient.FOUR_SQUARE_ID
import com.schaefer.playground.data.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface VenueService {
    @GET("/v2/venues/search")
    suspend fun searchVenue(
        @Query("ll") latLng: String,
        @Query("client_id") clientId: String = FOUR_SQUARE_ID,
        @Query("client_secret") clientSecret: String = FOUR_SQUARE_CLIENT,
        @Query("v") version: Int = 20190717,
    ): ApiResponse
}