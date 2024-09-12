package com.vts.co2checkertest.di

import com.vts.co2checkertest.domain.repository.TripRepository
import com.vts.co2checkertest.domain.usecase.AddNewTripUseCase
import com.vts.co2checkertest.domain.usecase.GetAllTripsUseCase
import com.vts.co2checkertest.domain.usecase.GetTripsByTransportCodeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideAddNewTripUseCase(repository: TripRepository): AddNewTripUseCase {
        return AddNewTripUseCase(repository)
    }

    @Provides
    fun provideGetAllTripsUseCase(repository: TripRepository): GetAllTripsUseCase {
        return GetAllTripsUseCase(repository)
    }

    @Provides
    fun provideGetTripsByTransportCodeUseCase(repository: TripRepository): GetTripsByTransportCodeUseCase {
        return GetTripsByTransportCodeUseCase(repository)
    }
}