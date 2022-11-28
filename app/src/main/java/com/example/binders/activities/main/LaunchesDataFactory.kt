package com.example.binders.activities.main

import com.example.binders.bindersadapters.model.LaunchViewModel
import com.kfilios.assesment.io.models.RocketLaunch

class LaunchesDataFactory {

    fun transformData(): (Array<RocketLaunch>) -> List<LaunchViewModel> = { data ->
        data.map { LaunchViewModel(it.missionName, it.details, it.links.missionPatchUrl, it.launchSuccess == true, it.flightNumber) }
    }
}