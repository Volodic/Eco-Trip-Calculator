package com.vts.co2checkertest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vts.co2checkertest.constants.TransportType
import com.vts.co2checkertest.data.model.TripData
import com.vts.co2checkertest.domain.usecase.GetTripsByTransportCodeUseCase
import com.vts.co2checkertest.ui.screen.statistics.StatisticsViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock

class StatisticsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test calculate CO2 emissions`() = runTest {
        val mockUseCase: GetTripsByTransportCodeUseCase = mock()
        val viewModel = StatisticsViewModel(mockUseCase)

        val carTrips = listOf(
            TripData(transportType = "Car", transportCode = 1, distance = 100.0, createdAt = 0),
            TripData(transportType = "Car", transportCode = 1, distance = 50.0, createdAt = 0)
        )

        `when`(mockUseCase.execute(TransportType.CAR.code))
            .thenReturn(carTrips)

        TransportType.entries.forEach { transportType ->
            if (transportType != TransportType.CAR) {
                `when`(mockUseCase.execute(transportType.code))
                    .thenReturn(emptyList())
            }
        }

        viewModel.loadData()
        advanceUntilIdle()

        val expectedCarEmissionsKg = 19.2f
        assertEquals(expectedCarEmissionsKg, viewModel.chartData.value.find { it.transportType == "Car" }?.value)
    }
}