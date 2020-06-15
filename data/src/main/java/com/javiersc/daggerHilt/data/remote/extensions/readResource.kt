package com.javiersc.daggerHilt.data.remote.extensions

import okhttp3.mockwebserver.MockResponse

internal infix fun Any.readResource(jsonFileName: String): String {
    return this::class.java.classLoader!!.getResource(jsonFileName)!!.readText()
}

internal infix fun String?.mockResponseWith(code: Int): MockResponse {
    return MockResponse().apply {
        setResponseCode(code)
        this@mockResponseWith?.let { setBody(readResource(it)) }
        setHeader("Content-Type", "application/json")
    }
}
