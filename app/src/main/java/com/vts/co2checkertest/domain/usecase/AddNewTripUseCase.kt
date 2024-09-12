package com.vts.co2checkertest.domain.usecase

import com.vts.co2checkertest.data.model.TripData
import com.vts.co2checkertest.domain.repository.TripRepository
import javax.inject.Inject

class AddNewTripUseCase @Inject constructor(private val repository: TripRepository) {
    suspend fun execute(tripData: TripData) = repository.addNewTrip(tripData)
}