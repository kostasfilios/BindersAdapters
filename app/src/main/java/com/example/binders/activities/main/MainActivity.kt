package com.example.binders.activities.main

import android.os.Bundle
import android.widget.Toast
import com.example.binders.abstraction.AdapterBinder
import com.example.binders.abstraction.getAdapterBinder
import com.example.binders.abstractionmodeling.BaseViewModelActivity
import com.example.binders.abstractionmodeling.Loading
import com.example.binders.abstractionmodeling.Success
import com.example.binders.abstractionmodeling.ViewState
import com.example.binders.bindersadapters.LaunchBinder
import com.example.binders.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseViewModelActivity<MainViewModel>() {

    override lateinit var binding: ActivityMainBinding
    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        viewModel.getData()
    }

    override fun initLayout() {
        binding.recycler.adapter = getAdapterBinder(
            LaunchBinder({})
        )
    }

    override fun render(state: ViewState) {
        when (state) {
            is Success<*> -> {
                (binding.recycler.adapter as? AdapterBinder)?.submitList(state.data as List<Any>)
            }
            is Error -> {
                Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
            }
            is Loading -> {}
            else -> {}
        }
    }
}