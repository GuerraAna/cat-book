package com.example.catbook.catsList.presentation.ui

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.catbook.R
import com.example.catbook.catsList.data.model.CatsResponse
import com.example.catbook.databinding.CatItemBinding

internal class CatsViewHolder(
    private val binding: CatItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    /**
     * Binds the [catItem] to the view, displaying the cat image. If the [catItem] is null,
     * displays a placeholder.
     */
    fun bind(catItem: CatsResponse?) {
        catItem?.let {
            setupImage(catItem)
        } ?: binding.catImage.setImageResource(R.drawable.ic_launcher_foreground)
    }

    private fun setupImage(catItem: CatsResponse) {
        binding.catImage.load(catItem.url) {
            placeholder(R.drawable.ic_launcher_foreground)
            error(R.drawable.ic_launcher_background)
            crossfade(true)
        }

        binding.catImage.setOnClickListener {
            Toast.makeText(binding.root.context, catItem.url, Toast.LENGTH_SHORT).show()
        }
    }
}
