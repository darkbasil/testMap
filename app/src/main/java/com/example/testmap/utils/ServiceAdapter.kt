package com.example.testmap.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class ServiceAdapter {
    @ToJson fun toJson(service: Service): String {
        return service.name
    }

    @FromJson fun fromJson(name: String): Service {
        return Service(name, true)
    }
}