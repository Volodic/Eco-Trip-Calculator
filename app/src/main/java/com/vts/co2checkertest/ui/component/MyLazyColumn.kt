package com.vts.co2checkertest.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vts.co2checkertest.data.model.TripData
import com.vts.co2checkertest.ui.theme.Typography
import com.vts.co2checkertest.utils.convertTimestampToDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomLazyColumn(
    trips: List<TripData>,
    maxItems: Int? = null
) {
    val groupedTrips = trips.groupBy { it.createdAt.convertTimestampToDate() }
    val displayTrips = maxItems?.let { trips.take(it) } ?: trips

    LazyColumn(
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (maxItems == null) {
            groupedTrips.forEach { (date, tripsOnDate) ->
                stickyHeader {
                    DateHeader(date = date)
                }

                items(tripsOnDate) { trip ->
                    ListItem(transportType = trip.transportType, distance = trip.distance)
                }
            }
        } else {
            items(displayTrips) { trip ->
                ListItem(transportType = trip.transportType, distance = trip.distance)
            }
        }
    }
}

@Composable
fun DateHeader(date: String) {
    Surface(
        color = Color.Transparent,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = date,
            style = Typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}