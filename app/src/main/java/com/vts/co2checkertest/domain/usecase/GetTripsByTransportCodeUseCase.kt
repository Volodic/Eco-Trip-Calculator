package com.vts.co2checkertest.domain.usecase

import com.vts.co2checkertest.data.model.TripData
import com.vts.co2checkertest.domain.repository.TripRepository
import javax.inject.Inject

class GetTripsByTransportCodeUseCase @Inject constructor(private val repository: TripRepository) {
    suspend fun execute(code: Int): List<TripData>? = repository.getTripsByTransportCode(code)
}