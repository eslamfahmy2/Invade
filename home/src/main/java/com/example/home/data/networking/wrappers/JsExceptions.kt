package com.example.home.data.networking.wrappers


class JsException(
    override val message: String?,
    val statusCode: Int? = null,
) : Exception()
