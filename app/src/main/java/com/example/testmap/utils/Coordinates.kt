package com.example.testmap.utils

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Coordinates (
    val lat: Double,
    val lng: Double
)