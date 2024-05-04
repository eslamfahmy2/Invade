package com.example.home.data.networking.wrappers

import retrofit2.Response


//decode response
suspend fun <T> unwrapResponseD(response: Response<T>): T {
    val body = response.body()
    if (response.isSuccessful && body != null) {
        return body
    } else {
        throw JsException(message = "error parsing", statusCode = 2)
    }
}





