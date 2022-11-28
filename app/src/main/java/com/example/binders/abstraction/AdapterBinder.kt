package com.example.binders.abstraction

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class AdapterBinder(private val viewBinders: Map<ItemClass, ItemBinder>) : ListAdapter<Any, BaseItemViewHolder<Any>>(
    GeneralDiffCallback(viewBinders)
) {

    private val viewTypeToBinders = viewBinders.mapKeys { it.value.getItemType() }

    private fun getViewBinder(viewType: Int): ItemBinder = viewTypeToBinders.getValue(viewType)

    override fun getItemViewType(position: Int): Int = viewBinders.getValue(super.getItem(position).javaClass).getItemType()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder<Any> {
        val holder = getViewBinder(viewType).createViewHolder(parent) as BaseItemViewHolder<Any>
        getViewBinder(viewType).postCreateViewHolder(holder)
        return holder
    }

    override fun onBindViewHolder(holder: BaseItemViewHolder<Any>, position: Int) {
        return getViewBinder(getItemViewType(position)).bindViewHolder(getItem(position), holder)
    }

    override fun onViewRecycled(holder: BaseItemViewHolder<Any>) {
        getViewBinder(holder.itemViewType).onViewRecycled(holder)
        super.onViewRecycled(holder)
    }

    override fun onViewAttachedToWindow(holder: BaseItemViewHolder<Any>) {
        super.onViewAttachedToWindow(holder)
        getViewBinder(holder.itemViewType).onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: BaseItemViewHolder<Any>) {
        getViewBinder(holder.itemViewType).onViewDetachedFromWindow(holder)
        super.onViewDetachedFromWindow(holder)
    }
}


typealias ItemClass = Class<out Any>
typealias ItemBinder = BaseItemViewBinder<Any, BaseItemViewHolder<Any>>

internal class GeneralDiffCallback(private val viewBinders: Map<ItemClass, ItemBinder>) : DiffUtil.ItemCallback<Any>() {

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        if (oldItem::class != newItem::class) {
            return false
        }

        return viewBinders[oldItem::class.java]?.areItemsTheSame(oldItem, newItem) ?: false
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return viewBinders[oldItem::class.java]?.areContentsTheSame(oldItem, newItem) ?: false
    }
}

fun LifecycleOwner.getAdapterBinder(vararg binders: BaseItemViewBinder<out Any, *>): AdapterBinder {
    val viewBinders = mutableMapOf<ItemClass, ItemBinder>()

    for (b in binders) {
        b.registerLifecycle(lifecycle)
        @Suppress("UNCHECKED_CAST")
        viewBinders[b.modelClass] = b as ItemBinder
    }

    return AdapterBinder(viewBinders)
}

fun getAdapterBinder(vararg binders: BaseItemViewBinder<out Any, *>): AdapterBinder {
    val viewBinders = mutableMapOf<ItemClass, ItemBinder>()

    for (b in binders) {
        @Suppress("UNCHECKED_CAST")
        viewBinders[b.modelClass] = b as ItemBinder
    }

    return AdapterBinder(viewBinders)
}


