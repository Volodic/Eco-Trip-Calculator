package com.vts.co2checkertest.constants

enum class Co2EmissionFactor(val transportType: TransportType, val emissionFactor: Float) {
    CAR(TransportType.CAR, 192.0f),
    BUS(TransportType.BUS, 105.0f),
    TRAIN(TransportType.TRAIN, 41.0f),
    BIKE(TransportType.BIKE, 0.0f)
}