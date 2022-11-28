package com.example.binders.bindersadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.binders.abstraction.BaseItemViewBinder
import com.example.binders.abstraction.BaseItemViewHolder
import com.example.binders.bindersadapters.model.LaunchViewModel
import com.example.binders.utils.setSafeOnClickListener
import com.example.binders.R
import com.example.binders.databinding.HolderLaunchBinding
import com.example.binders.utils.load

class LaunchBinder(private val callBack: (View) -> Unit): BaseItemViewBinder<LaunchViewModel, LaunchViewHolder>(LaunchViewModel::class.java) {
    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = HolderLaunchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LaunchViewHolder(binding, callBack)
    }

    override fun bindViewHolder(model: LaunchViewModel, viewHolder: LaunchViewHolder) = viewHolder.bind(model)

    override fun getItemType(): Int = R.layout.holder_launch

    override fun areItemsTheSame(oldItem: LaunchViewModel, newItem: LaunchViewModel): Boolean = true

    override fun areContentsTheSame(oldItem: LaunchViewModel, newItem: LaunchViewModel): Boolean = true
}

class LaunchViewHolder(private val binding: HolderLaunchBinding, private val callBack: (View) -> Unit): BaseItemViewHolder<LaunchViewModel>(binding.root){
    override fun bind(data: LaunchViewModel) {
        binding.root.setSafeOnClickListener(onSafeClick = callBack)
        binding.apply {
            missionName.text = data.missionName
            missionDetails.text = data.missionDetails
            if (data.missionImageUrl == null) return@apply
            missionPatch.load(data.missionImageUrl, strategy = DiskCacheStrategy.AUTOMATIC)
        }

    }
}