package com.example.binders.abstractionmodeling

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.delay

abstract class BaseViewModel(app: Application) : AndroidViewModel(app) {

    protected val stateLiveData = MutableLiveData<ViewState>()

    var isLoading = false
        private set

    fun observeState(owner: LifecycleOwner, observer: Observer<ViewState>) {
        stateLiveData.removeObservers(owner)
        stateLiveData.observe(owner, observer)
    }

    fun clearLiveData() {}

    open suspend fun startLoading() {
        isLoading = true
        delay(500)
        stateLiveData.postValue(Loading())
    }

    open suspend fun stopLoading() {
        isLoading = false
        stateLiveData.postValue(Loading(isLoading))
        delay(500)
    }

    abstract fun getData()
}