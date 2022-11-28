package com.kfilios.assesment.io.controller

import com.kfilios.assesment.io.models.RocketLaunch
import com.kfilios.assesment.io.request.SpaceXLaunchesRequest
import com.kfilios.assesment.io.service.BaseController
import com.kfilios.assesment.io.service.NetworkResponse

//https://docs.spacexdata.com/?version=latest#32f4fc1e-37e8-4d1b-8ec4-ac729441ddb2

class SpaceXLaunchesController : BaseController<Array<RocketLaunch>>() {

    // ===========================================================
    // Constants
    // ===========================================================

    private val request: SpaceXLaunchesRequest = SpaceXLaunchesRequest()

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    override suspend fun fetchData(): NetworkResponse {
        return doSuspendRequest<Array<RocketLaunch>>(request)
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