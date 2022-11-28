package com.example.binders.utils

import android.os.SystemClock
import android.view.View

open class SafeClickListener(
    var interval: Int = 1000,
    private val onSafeClick: (View) -> Unit
) : View.OnClickListener {

    private var lastTimeClicked: Long = 0

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < interval) return
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeClick(v)
    }
}