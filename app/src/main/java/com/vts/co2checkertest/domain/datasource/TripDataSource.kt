package com.vts.co2checkertest.domain.datasource

import com.vts.co2checkertest.data.model.TripData

interface TripDataSource {
    suspend fun getAllTrips(): List<TripData>?
    suspend fun getTripsByTransportCode(code: Int): List<TripData>?
    suspend fun addNewTrip(tripData: TripData)
}