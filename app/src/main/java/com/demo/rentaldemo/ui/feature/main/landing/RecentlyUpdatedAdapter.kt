package com.demo.rentaldemo.ui.feature.main.landing

import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.demo.rentaldemo.R
import com.demo.rentaldemo.databinding.ItemRecentlyUpdatedBinding
import com.demo.rentaldemo.ui.base.BaseAdapter
import com.demo.rentaldemo.ui.base.BaseViewHolder
import com.demo.rentaldemo.ui.feature.main.data.model.RecentlyUpdatedResponse

class RecentlyUpdatedAdapter(
    private var dataList: MutableList<RecentlyUpdatedResponse>,
    private val onItemSelectedListener: (RecentlyUpdatedResponse) -> Unit
) : BaseAdapter<RecentlyUpdatedResponse, RecentlyUpdatedAdapter.RecentlyUpdatedViewHolder>() {

    override fun getViewHolder(binding: ViewDataBinding, viewType: Int): RecentlyUpdatedViewHolder {
        return RecentlyUpdatedViewHolder(binding as ItemRecentlyUpdatedBinding)
    }

    override fun onBindCustomViewHolder(holder: RecentlyUpdatedViewHolder, position: Int) {
        return holder.bindView(dataList[position])
    }

    override fun getLayoutResource(viewType: Int): Int {
        return R.layout.item_recently_updated
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(list: MutableList<RecentlyUpdatedResponse>) {
        this.dataList = list
    }

    inner class RecentlyUpdatedViewHolder(private var binding: ItemRecentlyUpdatedBinding) :
        BaseViewHolder<RecentlyUpdatedResponse>(binding) {
        override fun bindView(obj: RecentlyUpdatedResponse) {
            super.bindView(obj)
            binding.tvTitle.text = obj.title
            Glide.with(binding.root.context)
                .load(obj.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.logo).into(binding.ivRecentlyUpdated)

            binding.tvPer.text = "/ ${obj.price_type}"
            binding.tvAddress.text = obj.address
            binding.tvRoomCount.text = "${obj.roomCount} room"
            binding.tvPrice.text = "${obj.currency_code}${obj.price}"
            binding.tvStatus.text = obj.status


            binding.cvRecentlyUpdated.setOnClickListener {
                onItemSelectedListener(obj)
            }
        }
    }
}