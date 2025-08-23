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
import com.example.catbook.catsList.data.model.CatsResponse
import com.example.catbook.catsList.presentation.CatsState
import com.example.catbook.catsList.presentation.CatsViewModel
import com.example.catbook.databinding.ActivityCatsBinding
import kotlinx.coroutines.launch

internal class CatsActivity : AppCompatActivity() {

    private val binding: ActivityCatsBinding by lazy { ActivityCatsBinding.inflate(layoutInflater) }
    private val viewModel: CatsViewModel by viewModels()
    private val catAdapter: CatsAdapter by lazy { CatsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViewModel()
    }

    private fun setupViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) { collectCatListState() }
        }
    }

    private suspend fun collectCatListState() {
        viewModel.state.collect { state ->
            when (state) {
                is CatsState.Loading -> onLoading()
                is CatsState.Success -> onSuccess(state)
                is CatsState.Error -> onError()
                is CatsState.Empty -> onEmpty()
            }
        }
    }

    private fun onEmpty() {
        Log.d("MainActivity", "Empty")
        Toast.makeText(this@CatsActivity, "Empty", Toast.LENGTH_SHORT).show()
    }

    private fun onError() {
        Log.e("MainActivity", "Error")
        Toast.makeText(this@CatsActivity, "Error", Toast.LENGTH_SHORT).show()
    }

    private fun onSuccess(state: CatsState.Success) {
        Log.d("MainActivity", "Success")
        Toast.makeText(this@CatsActivity, "Success", Toast.LENGTH_SHORT).show()
        setupCatList(state.response)
    }

    private fun onLoading() {
        Log.d("MainActivity", "Loading")
        Toast.makeText(this@CatsActivity, "Loading", Toast.LENGTH_SHORT).show()
    }

    private fun setupCatList(response: List<CatsResponse>) {
        catAdapter.submitList(response)
        binding.catList.adapter = catAdapter
        binding.catList.isVisible = true
    }
}
