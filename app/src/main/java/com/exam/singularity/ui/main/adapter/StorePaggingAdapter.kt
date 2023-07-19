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
import com.exam.singularity.ui.main.model.StoreResponse.StoreDataModel

private val Comparator = object : DiffUtil.ItemCallback<StoreDataModel>() {
    override fun areItemsTheSame(oldItem: StoreDataModel, newItem: StoreDataModel) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: StoreDataModel, newItem: StoreDataModel) = oldItem == newItem
}
class StorePaggingAdapter(val context: Context) :
    PagingDataAdapter<StoreDataModel, StorePaggingAdapter.ViewHolder>(Comparator) {

    var clickItem: ItemOnClickListener<StoreDataModel>? = null

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