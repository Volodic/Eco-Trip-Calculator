package com.vts.co2checkertest.domain.repository

import com.vts.co2checkertest.data.model.TripData
import com.vts.co2checkertest.domain.datasource.TripDataSource
import javax.inject.Inject

class TripRepositoryImpl @Inject constructor(private val repository: TripDataSource): TripRepository {

    override suspend fun getAllTrips(): List<TripData>? = repository.getAllTrips()

    override suspend fun getTripsByTransportCode(code: Int): List<TripData>? = repository.getTripsByTransportCode(code)

    override suspend fun addNewTrip(tripData: TripData) = repository.addNewTrip(tripData)
}