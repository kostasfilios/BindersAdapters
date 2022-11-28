package com.kfilios.assesment.io.service

import com.github.kittinunf.fuel.core.FuelError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

open class BaseController<T> : ServiceSuspend() {

    // ===========================================================
    // Constants
    // ===========================================================

    protected val fetchTriesThreshold = 4
    protected var fetchDataTries = 0

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    open suspend fun fetchData(): NetworkResponse {
        return NetworkResponse.Error(Exception("Not implemented"))
    }

//    open fun destroy() {
//        disposable.dispose()
//    }
//
//    protected fun handleException(error: Throwable) {
//        if (error is FuelError) {
//            val serverExceptionException = ServerException(error.response)
//            streamErrors.onNext(serverExceptionException)
//        } else {
//            streamErrors.onNext(Exception(error.message, error.cause))
//        }
//    }
    
    protected  fun canRetryFetch(): Boolean {
        return fetchDataTries < fetchTriesThreshold
    }

    fun addRetryJob() {
        CoroutineScope(Dispatchers.IO).async {
            fetchDataTries += 1
            delay(2000)
            fetchData()
        }
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}