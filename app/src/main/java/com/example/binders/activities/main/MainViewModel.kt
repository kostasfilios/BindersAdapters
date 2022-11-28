package com.example.binders.activities.main

import android.widget.Toast
import androidx.lifecycle.*
import com.example.binders.BinderApplication
import com.example.binders.abstractionmodeling.BaseViewModel
import com.example.binders.abstractionmodeling.Success
import com.example.binders.abstractionmodeling.ViewState
import com.example.binders.bindersadapters.model.LaunchViewModel
import com.example.binders.utils.curriedChain
import com.example.binders.utils.tryCast
import com.kfilios.assesment.io.controller.SpaceXLaunchesController
import com.kfilios.assesment.io.models.RocketLaunch
import com.kfilios.assesment.io.service.NetworkResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel(application: BinderApplication, private val dataFactory: LaunchesDataFactory ) : BaseViewModel(application) {

    private val launchesController = SpaceXLaunchesController()
    val dataTransformComp = handleResponse()
        .curriedChain(dataFactory.transformData())
        .curriedChain(updateUI())

    override fun getData() {
        GlobalScope.launch {
            val networkFlowResponse = flow {
                emit(launchesController.fetchData())
            }
            networkFlowResponse.collect(dataTransformComp)
        }
    }

    private fun handleResponse(): (NetworkResponse) -> Array<RocketLaunch> {
        return fun(response): Array<RocketLaunch> {
            return when (response) {
                is NetworkResponse.Success<*> -> {
                    response.result as Array<RocketLaunch>
                }
                is NetworkResponse.Error -> {
                    response.error
                    emptyArray()
                }
            }
        }
    }

    private fun updateUI(): (List<LaunchViewModel>) -> Unit = { modelList ->
        stateLiveData.postValue(Success(modelList))
    }
}