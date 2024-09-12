package com.vts.co2checkertest.di

import com.vts.co2checkertest.data.db.AppDatabase
import com.vts.co2checkertest.domain.datasource.TripDataSource
import com.vts.co2checkertest.domain.datasource.TripDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideTripDataSource(appDatabase: AppDatabase): TripDataSource {
        return TripDataSourceImpl(appDatabase)
    }
}