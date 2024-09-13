package com.vts.co2checkertest.ui.screen.statistics

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vts.co2checkertest.constants.Co2EmissionFactor
import com.vts.co2checkertest.constants.TransportColor
import com.vts.co2checkertest.constants.TransportType
import com.vts.co2checkertest.data.model.ChartData
import com.vts.co2checkertest.domain.usecase.GetTripsByTransportCodeUseCase
import com.vts.co2checkertest.ui.theme.Gray
import com.vts.co2checkertest.utils.toFixedDecimalPlaces
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val getTripsByTransportCodeUseCase: GetTripsByTransportCodeUseCase
) : ViewModel() {

    private val _chartData = mutableStateOf(listOf<ChartData>())
    val chartData = _chartData

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val trips = mutableListOf<ChartData>()

            TransportType.entries.forEach { transportType ->
                val tripList = getTripsByTransportCodeUseCase.execute(transportType.code)

                val transportColor =
                    TransportColor.entries.firstOrNull { it.transportType == transportType }?.color
                        ?: Gray
                val totalDistance = tripList?.sumOf { it.distance }
                val emissionFactor =
                    Co2EmissionFactor.entries.firstOrNull { it.transportType == transportType }?.emissionFactor
                        ?: 0f
                val totalEmissionsKg =
                    (totalDistance?.times(emissionFactor))?.div(1000)?.toFixedDecimalPlaces(2)

                trips.add(
                    ChartData(
                        value = totalEmissionsKg,
                        color = transportColor,
                        transportType = transportType.displayName
                    )
                )
            }

            _chartData.value = trips
        }
    }
}