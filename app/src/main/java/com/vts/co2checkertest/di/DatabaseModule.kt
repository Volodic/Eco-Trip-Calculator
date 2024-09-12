package com.vts.co2checkertest.di

import android.content.Context
import androidx.room.Room
import com.vts.co2checkertest.data.dao.TripDao
import com.vts.co2checkertest.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "trip_core"
        )
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    @Provides
    fun provideTripDao(appDatabase: AppDatabase): TripDao {
        return appDatabase.tripDao()
    }
}