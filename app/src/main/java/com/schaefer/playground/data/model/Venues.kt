package com.schaefer.playground.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Venues(
    val venues: List<VenueData>,
)