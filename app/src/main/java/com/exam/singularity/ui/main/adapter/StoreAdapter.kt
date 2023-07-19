package com.exam.singularity.ui.main.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.exam.singularity.core.listener.ItemOnClickListener
import com.exam.singularity.databinding.RowItemStoreSelectBinding
import com.exam.singularity.ui.main.model.StoreResponse

private val Comparator = object : DiffUtil.ItemCallback<StoreResponse.StoreDataModel>() {
    override fun areItemsTheSame(oldItem: StoreResponse.StoreDataModel, newItem: StoreResponse.StoreDataModel) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: StoreResponse.StoreDataModel, newItem: StoreResponse.StoreDataModel) = oldItem == newItem
}
class StoreAdapter(val context: Context) :
    PagingDataAdapter<StoreResponse.StoreDataModel, StoreAdapter.ViewHolder>(Comparator) {

    var clickItem: ItemOnClickListener<StoreResponse.StoreDataModel>? = null

    inner class ViewHolder(val binding: RowItemStoreSelectBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RowItemStoreSelectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { store ->
            with(holder) {
                binding.apply {

                        if (position % 2 == 0) rootView.setBackgroundColor(Color.parseColor("#E5E5E5"))
                    else rootView.setBackgroundColor(Color.parseColor("#FFFFFF"))

                    tvStoreName.text = store.name

                    root.setOnClickListener {



                        clickItem?.onClick(store)
                    }



                }
            }
        }
    }



}