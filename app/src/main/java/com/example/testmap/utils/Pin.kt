package com.example.testmap.utils

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Pin(
    val id: Int,
    val service: String,
    val coordinates: Coordinates
)