package com.kfilios.assesment.io.service

import com.example.binders.BuildConfig

abstract class Request {

    // ===========================================================
    // Constants
    // ===========================================================

    enum class Method {
        GET,
        POST
    }

    // ===========================================================
    // Fields
    // ===========================================================

    val defaultHeaders: MutableMap<String, String>
    get() {
        return mutableMapOf("Content-Type" to "application/json")
    }

    val defaultUrlParams: MutableMap<String, String>
        get() {
            return mutableMapOf<String, String>()
        }

    abstract var method: Method
    open var path: String = ""
    open val baseUrl: String = BuildConfig.BASE_URL
    open var header: Map<String, String>? = null
    open var body: String? = null
    open var queryParameter: MutableMap<String, String>? = null

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

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