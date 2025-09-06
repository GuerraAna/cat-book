package com.example.catbook.catsList.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.catbook.catsList.data.model.CatsResponse
import com.example.catbook.databinding.CatItemBinding

internal class CatsAdapter : PagingDataAdapter<CatsResponse, CatsViewHolder>(CatDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val binding = CatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        holder.bind(catItem = getItem(position))
    }

    private class CatDiffCallback : DiffUtil.ItemCallback<CatsResponse>() {
        override fun areItemsTheSame(oldItem: CatsResponse, newItem: CatsResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CatsResponse, newItem: CatsResponse): Boolean {
            return oldItem == newItem
        }
    }
}
