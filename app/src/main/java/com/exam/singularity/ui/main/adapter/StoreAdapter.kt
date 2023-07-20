package com.exam.singularity.ui.main.adapter


import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exam.singularity.core.listener.ItemOnClickListener
import com.exam.singularity.databinding.RowItemStoreSelectBinding
import com.exam.singularity.ui.main.model.StoreResponse.StoreDataModel


class StoreAdapter(val context: Context) :
    RecyclerView.Adapter<StoreAdapter.ViewHolder>() {

    var modelList = ArrayList<StoreDataModel>()

    var clickItem: ItemOnClickListener<StoreDataModel>? = null


    fun submitData(list: ArrayList<StoreDataModel>) {

        modelList = list
        notifyDataSetChanged()
    }
    fun updateData(list: ArrayList<StoreDataModel>) {
        list.onEach { element->
            modelList.add(element)
        }

        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: RowItemStoreSelectBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            RowItemStoreSelectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = modelList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = modelList[position]

        with(holder) {

            binding.apply {
                tvStoreName.text = (item.name)
                root.setOnClickListener {


                    clickItem?.onClick(item)
                }

                if (position % 2 == 0) rootView.setBackgroundColor(Color.parseColor("#E5E5E5"))
                else rootView.setBackgroundColor(Color.parseColor("#FFFFFF"))


            }
        }
    }
}



