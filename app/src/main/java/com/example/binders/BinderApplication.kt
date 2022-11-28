package com.example.binders

import android.app.Application
import androidx.lifecycle.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BinderApplication: Application(), LifecycleObserver {

    private var fcmLiveData: MutableLiveData<String> = MutableLiveData()

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@BinderApplication)
            modules(listOf(Modules(this@BinderApplication).all))
        }
    }
}