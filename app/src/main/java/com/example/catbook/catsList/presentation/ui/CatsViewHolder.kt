package com.example.catbook.catsList.presentation.ui

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.catbook.R
import com.example.catbook.catsList.data.model.CatsResponse
import com.example.catbook.databinding.CatItemBinding

internal class CatsViewHolder(
    private val binding: CatItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    /**
     * 
     */
    fun bind(catItem: CatsResponse) {
        binding.catImage.load(catItem.url) {
            placeholder(R.drawable.ic_launcher_foreground)
            error(R.drawable.ic_launcher_background)
            crossfade(true)
        }
    }
}
