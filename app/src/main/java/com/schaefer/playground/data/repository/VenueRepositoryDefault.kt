package com.schaefer.playground.data.repository

import com.schaefer.playground.data.local.RemoteDataSource
import com.schaefer.playground.domain.model.Location
import com.schaefer.playground.domain.model.Venue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VenueRepositoryDefault(
    private val remoteDataSource: RemoteDataSource = RemoteDataSource(),
) {
    fun getVenue(lat: Double, lng: Double): Flow<List<Venue>> {
        return flow {
            remoteDataSource.getVenue(lat = lat, lng = lng).onSuccess { venues ->
                emit(
                    venues.venues.map {
                        Venue(
                            id = it.id,
                            name = it.name,
                            formattedAddress = it.formattedAddress.orEmpty(),
                            location = Location(
                                lat = it.location?.lat ?: 0.0,
                                lng = it.location?.lng ?: 0.0,
                                address = it.location?.address.orEmpty()
                            )
                        )
                    },
                )
            }.onFailure {
                throw it
            }
        }
    }
}