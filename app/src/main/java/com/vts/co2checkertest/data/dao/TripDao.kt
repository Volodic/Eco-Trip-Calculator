package com.vts.co2checkertest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vts.co2checkertest.data.model.TripData

@Dao
interface TripDao {

    @Query("SELECT * FROM trip")
    suspend fun getAllTrips(): List<TripData>?

    @Query("SELECT * FROM trip WHERE transportCode = :code")
    suspend fun getTripsByTransportCode(code: Int): List<TripData>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewTrip(tripData: TripData)
}