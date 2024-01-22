package com.schaefer.playground.domain.model

data class Venue(
    val id: String,
    val name: String,
    val location: Location,
    val formattedAddress: String,
)