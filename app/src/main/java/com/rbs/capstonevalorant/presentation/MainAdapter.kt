package com.rbs.capstonevalorant.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rbs.capstonevalorant.R
import com.rbs.capstonevalorant.databinding.ItemListBinding
import com.rbs.core.domain.model.Agents

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var listData = ArrayList<Agents>()
    var onItemClick: ((Agents) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Agents>?) {
        if (newListData != null) {
            listData.clear()
            listData.addAll(newListData)
            notifyDataSetChanged()
        }
    }

//    fun setData(newListData: List<Agents>) {
//        val diffCallback = DiffCallback(listData, newListData)
//        val diffResult = DiffUtil.calculateDiff(diffCallback)
//        listData.clear()
//        listData.addAll(newListData)
//        diffResult.dispatchUpdatesTo(this)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list, parent, false
            )
        )

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListBinding.bind(itemView)
        fun bind(data: Agents) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(ivAgent)
                tvName.text = data.name
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}