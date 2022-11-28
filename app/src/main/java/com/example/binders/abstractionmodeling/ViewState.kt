package com.example.binders.abstractionmodeling

sealed class ViewState

data class Loading(val loading: Boolean = true): ViewState()
data class Error(val message: String? = null): ViewState()
data class Success<T>(val data: T): ViewState()