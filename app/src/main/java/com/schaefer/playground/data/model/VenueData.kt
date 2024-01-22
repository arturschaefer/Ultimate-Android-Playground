package com.schaefer.playground.data.model

import kotlinx.serialization.Serializable

@Serializable
data class VenueData(
    val id: String,
    val name: String,
    val formattedAddress: String? = null,
    val location: LocationData?,
)