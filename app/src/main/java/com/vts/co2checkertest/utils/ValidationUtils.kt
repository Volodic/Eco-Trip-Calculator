package com.vts.co2checkertest.utils

fun validateTransportSelection(selectedTransport: String): Boolean {
    return selectedTransport.isNotEmpty()
}

fun validateDistanceInput(distance: String): Boolean {
    val distanceValue = distance.replace(',', '.')
    return distanceValue.isNotEmpty() && distanceValue.toFloatOrNull() != null
}

fun formatDistanceInput(distance: String): String {
    return distance.replace(',', '.')
}