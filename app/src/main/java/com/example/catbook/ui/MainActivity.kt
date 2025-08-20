package com.example.catbook.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catbook.CatsState
import com.example.catbook.CatsViewModel
import com.example.catbook.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

internal class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: CatsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
    }

    private fun setupViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when {
                        state.isLoading -> {
                            Log.d("MainActivity", "Loading")
                            Toast.makeText(this@MainActivity, "Loading", Toast.LENGTH_SHORT).show()
                        }

                        state.isEmpty -> {
                            Log.d("MainActivity", "Empty")
                            Toast.makeText(this@MainActivity, "Empty", Toast.LENGTH_SHORT).show()
                        }

                        state.isError -> {
                            Log.e("MainActivity", "Error")
                            Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                        }

                        else -> {
                            setupView(state)
                        }
                    }
                }
            }
        }
    }

    private fun setupView(state: CatsState) {
        state.response?.let { response ->
            binding.catList.layoutManager = LinearLayoutManager(this)
            val catAdapter = CatsAdapter()
            catAdapter.submitList(response)
            binding.catList.adapter = catAdapter
            binding.catList.isVisible = true
        } ?: {
            binding.catList.isVisible = false
        }
    }
}
