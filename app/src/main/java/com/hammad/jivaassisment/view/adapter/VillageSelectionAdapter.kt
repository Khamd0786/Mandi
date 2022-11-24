package com.hammad.jivaassisment.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hammad.jivaassisment.databinding.ItemVillageBinding

class VillageSelectionAdapter(private val callback: OnVillageSelectListener? = null): ListAdapter<String, VillageSelectionAdapter.VH>(Comparator) {

    inner class VH(private val binding: ItemVillageBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(village: String) {
            binding.text.text = village
            binding.root.setOnClickListener { callback?.onVillageSelect(village, adapterPosition) }
        }
    }


    private object Comparator: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemVillageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }


    fun interface OnVillageSelectListener {
        fun onVillageSelect(village: String, position: Int)
    }
}