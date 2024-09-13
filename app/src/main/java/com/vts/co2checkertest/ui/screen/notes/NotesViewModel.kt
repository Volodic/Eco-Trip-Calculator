package com.vts.co2checkertest.ui.screen.notes

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vts.co2checkertest.constants.TransportType
import com.vts.co2checkertest.data.model.TripData
import com.vts.co2checkertest.domain.usecase.AddNewTripUseCase
import com.vts.co2checkertest.domain.usecase.GetAllTripsUseCase
import com.vts.co2checkertest.utils.getCurrentTimeAsLong
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val addNewTripUseCase: AddNewTripUseCase,
    private val getAllTripsUseCase: GetAllTripsUseCase
) : ViewModel() {

    private val _trips = mutableStateOf(listOf<TripData>())
    val trips = _trips

    fun addNewTrip(transportType: String, distance: Double) {
        viewModelScope.launch {
            val currentTime = getCurrentTimeAsLong()
            val transportCode = TransportType.entries.find { it.displayName == transportType }?.code ?: 0

            val trip = TripData(
                transportType = transportType,
                transportCode = transportCode,
                distance = distance,
                createdAt = currentTime
            )

            addNewTripUseCase.execute(trip)
            getAllTrips()
        }
    }

    fun getAllTrips() = viewModelScope.launch {
        val allTrips = getAllTripsUseCase.execute()

        _trips.value = allTrips?.reversed() ?: listOf()
    }
}