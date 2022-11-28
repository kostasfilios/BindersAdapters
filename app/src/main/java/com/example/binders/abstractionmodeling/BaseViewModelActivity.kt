package com.example.binders.abstractionmodeling

import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseViewModelActivity<VM: BaseViewModel> : AppCompatActivity() {

    protected abstract val binding: ViewBinding
    protected abstract val viewModel: VM
    private var activityResultCallBack: ((ActivityResult) -> Unit)? = null

    abstract fun initLayout()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        viewModel.observeState(this) {
            render(it)
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.clearLiveData()
    }

    open fun render(state: ViewState) {}
}