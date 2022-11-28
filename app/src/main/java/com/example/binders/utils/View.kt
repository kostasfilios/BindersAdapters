package com.example.binders.utils

import android.os.CountDownTimer
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator


fun <V : View> V.setSafeOnClickListener(interval: Int = 2000, onSafeClick: (V) -> Unit) {
    val safeClickListener = SafeClickListener(interval) {
        (it as? V)?.let { it1 -> onSafeClick(it1) }
    }
    setOnClickListener(safeClickListener)
}

fun View.setSafeOnClickListener(clickListener: View.OnClickListener?, interval: Int = 2000) {
    val safeClickListener = SafeClickListener(interval) {
        clickListener?.onClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun RecyclerView.disableChangeAnimation() {
    val animator = itemAnimator
    if (animator is SimpleItemAnimator) {
        animator.supportsChangeAnimations = false
    }
}