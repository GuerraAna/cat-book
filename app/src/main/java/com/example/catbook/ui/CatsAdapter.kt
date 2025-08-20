package com.example.catbook.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.catbook.databinding.CatItemBinding
import com.example.catbook.model.CatsResponse

internal class CatsAdapter() : ListAdapter<CatsResponse, CatsViewHolder>(CatDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val binding = CatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        val breed = getItem(position)
        holder.bind(breed)
    }

//    override fun getItemCount(): Int = catList.size

    private class CatDiffCallback : DiffUtil.ItemCallback<CatsResponse>() {
        override fun areItemsTheSame(oldItem: CatsResponse, newItem: CatsResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CatsResponse, newItem: CatsResponse): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
