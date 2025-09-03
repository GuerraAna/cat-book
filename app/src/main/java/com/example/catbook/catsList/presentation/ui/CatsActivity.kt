package com.example.catbook.catsList.presentation.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
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
                // Coleta os dados paginados
                viewModel.catsPagingData.collectLatest { pagingData ->
                    catAdapter.submitData(pagingData)
                }
            }
        }

        // Opcional: Monitora o estado de carregamento
        lifecycleScope.launch {
            catAdapter.loadStateFlow.collectLatest { loadStates ->
                when (val refresh = loadStates.refresh) {
                    is LoadState.Loading -> {
                        Log.d("CatsActivity", "Loading...")
                        // Mostrar loading
                    }

                    is LoadState.Error -> {
                        Log.e("CatsActivity", "Error: ${refresh.error.message}")
                        Toast.makeText(this@CatsActivity, "Error loading cats", Toast.LENGTH_SHORT).show()
                    }

                    is LoadState.NotLoading -> {
                        Log.d("CatsActivity", "Loaded successfully")
                        // Esconder loading
                    }
                }
            }
        }
    }

    private fun refreshCats() {
        catAdapter.refresh()
    }
}

