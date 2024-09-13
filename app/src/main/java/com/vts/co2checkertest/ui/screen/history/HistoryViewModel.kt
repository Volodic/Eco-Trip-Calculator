package com.vts.co2checkertest.ui.screen.history

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vts.co2checkertest.data.model.TripData
import com.vts.co2checkertest.domain.usecase.GetAllTripsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getAllTripsUseCase: GetAllTripsUseCase
) : ViewModel() {

    private val _userTrips = mutableStateOf(listOf<TripData>())
    val userTrips = _userTrips

    fun loadData() {
        viewModelScope.launch {
            val savedTrips = getAllTripsUseCase.execute()?.reversed()

            if (savedTrips != null) {
                _userTrips.value = savedTrips
            }
        }
    }
}