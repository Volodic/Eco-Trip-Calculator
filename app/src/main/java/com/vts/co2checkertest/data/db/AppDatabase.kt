package com.vts.co2checkertest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vts.co2checkertest.data.dao.TripDao
import com.vts.co2checkertest.data.model.TripData

@Database(entities = [TripData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tripDao(): TripDao
}