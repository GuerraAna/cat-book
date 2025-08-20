package com.example.catbook.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.catbook.databinding.CatItemBinding
import com.example.catbook.model.CatsResponse

internal class CatsViewHolder(
    private val binding: CatItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    /**
     * 
     */
    fun bind(catItem: CatsResponse) {
        binding.itemTextView.text = catItem.id
    }
}
