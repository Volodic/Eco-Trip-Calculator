package com.vts.co2checkertest.di

import com.vts.co2checkertest.domain.datasource.TripDataSource
import com.vts.co2checkertest.domain.repository.TripRepository
import com.vts.co2checkertest.domain.repository.TripRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTripRepository(tripDataSource: TripDataSource): TripRepository {
        return TripRepositoryImpl(tripDataSource)
    }
}