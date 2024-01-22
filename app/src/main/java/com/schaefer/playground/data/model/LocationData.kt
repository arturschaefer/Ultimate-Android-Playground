package com.schaefer.playground.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LocationData(
    val lat: Double,
    val lng: Double,
    val address: String? = null,
)
