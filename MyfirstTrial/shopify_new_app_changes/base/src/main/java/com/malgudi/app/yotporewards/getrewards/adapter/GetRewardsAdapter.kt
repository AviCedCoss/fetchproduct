package com.malgudi.app.yotporewards.getrewards.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.malgudi.app.R
import com.malgudi.app.databinding.GetrewadsItemBinding
import com.malgudi.app.yotporewards.getrewards.model.GetRewardModel
import com.malgudi.app.yotporewards.getrewards.model.GetRewardModelItem
import javax.inject.Inject

class GetRewardsAdapter @Inject constructor() : RecyclerView.Adapter<GetRewardsAdapter.GetRewardsViewHolder>() {
    private var getRewardModel: GetRewardModel? = null
    private var clickCallback: ClickCallback? = null
    fun setData(getRewardModel: GetRewardModel, clickCallback: ClickCallback) {
        this.getRewardModel = getRewardModel
        this.clickCallback = clickCallback
    }

    class GetRewardsViewHolder : RecyclerView.ViewHolder {
        var getrewadsItemBinding: GetrewadsItemBinding

        constructor(itemView: GetrewadsItemBinding) : super(itemView.root) {
            this.getrewadsItemBinding = itemView
        }
    }

    interface ClickCallback {
        fun redeemPoint(rewardItem: GetRewardModelItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetRewardsViewHolder {
        var view = DataBindingUtil.inflate<GetrewadsItemBinding>(LayoutInflater.from(parent.context), R.layout.getrewads_item, parent, false)
        return GetRewardsViewHolder(view)
    }

    override fun onBindViewHolder(holder: GetRewardsViewHolder, position: Int) {
        holder.getrewadsItemBinding.getrewardmodel = getRewardModel?.get(position)
        holder.getrewadsItemBinding.rewardpoint.setOnClickListener {
            clickCallback?.redeemPoint(getRewardModel?.get(position)!!)
        }
    }

    override fun getItemCount(): Int {
        return getRewardModel?.size ?: 0
    }
}