package com.vts.co2checkertest.domain.datasource

import com.vts.co2checkertest.data.db.AppDatabase
import com.vts.co2checkertest.data.model.TripData
import javax.inject.Inject

class TripDataSourceImpl @Inject constructor(private val appDatabase: AppDatabase) : TripDataSource {
    override suspend fun getAllTrips(): List<TripData>? = appDatabase.tripDao().getAllTrips()

    override suspend fun getTripsByTransportCode(code: Int): List<TripData>? = appDatabase.tripDao().getTripsByTransportCode(code)

    override suspend fun addNewTrip(tripData: TripData) = appDatabase.tripDao().addNewTrip(tripData)
}