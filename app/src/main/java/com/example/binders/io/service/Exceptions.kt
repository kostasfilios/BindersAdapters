package com.kfilios.assesment.io.service

import com.github.kittinunf.fuel.core.Response

data class ServerException(val code: Int, val url: String?, val reason: String?) : Exception(reason) {
    constructor(response: Response) : this(response.statusCode, response.url.toExternalForm(), response.responseMessage)
    constructor(method: String, exception: Throwable?) : this(9000, method, exception?.message)
    constructor(method: String, reason: String?) : this(9000, method, reason)
}

data class NoInternetException(private val method: String) : Exception("NO_INTERNET")