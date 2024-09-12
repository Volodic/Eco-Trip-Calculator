package com.vts.co2checkertest.domain.repository

import com.vts.co2checkertest.data.model.TripData

interface TripRepository {
    suspend fun getAllTrips(): List<TripData>?
    suspend fun getTripsByTransportCode(code: Int): List<TripData>?
    suspend fun addNewTrip(tripData: TripData)
}