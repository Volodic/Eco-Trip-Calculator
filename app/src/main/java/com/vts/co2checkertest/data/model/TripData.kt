package com.vts.co2checkertest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trip")
data class TripData(
    @PrimaryKey(autoGenerate = true) val tripId: Long = 0L,
    val transportType: String,
    val transportCode: Int,
    val distance: Double,
    val createdAt: Long
)
