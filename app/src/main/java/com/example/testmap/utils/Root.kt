package com.example.testmap.utils

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Root (
    val services: List<Service>,
    val pins: List<Pin>
)