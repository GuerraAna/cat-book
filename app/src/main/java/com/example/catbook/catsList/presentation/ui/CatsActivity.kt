package com.example.catbook.catsList.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.catbook.catsList.presentation.CatsViewModel
import com.example.catbook.databinding.ActivityCatsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal class CatsActivity : AppCompatActivity() {

    private val binding: ActivityCatsBinding by lazy { ActivityCatsBinding.inflate(layoutInflater) }
    private val viewModel: CatsViewModel by viewModels()
    private val catAdapter: CatsAdapter by lazy { CatsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupRecyclerView() {
        binding.catList.adapter = catAdapter
        binding.catList.isVisible = true
    }

    private fun setupViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.catsPagingData.collectLatest { pagingData ->
                    catAdapter.submitData(pagingData)
                }
            }
        }
    }
}

