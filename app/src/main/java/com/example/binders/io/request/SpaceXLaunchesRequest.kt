package com.kfilios.assesment.io.request

import com.kfilios.assesment.io.service.Request

class SpaceXLaunchesRequest(): Request() {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    override var method: Method
        get() = Method.GET
        set(value) {}

    override var path: String = "/launches"
}