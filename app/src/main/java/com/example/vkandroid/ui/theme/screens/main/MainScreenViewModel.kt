package com.example.vkandroid.ui.theme.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkandroid.ProductUIModel
import com.example.vkandroid.api.ApiResult
import com.example.vkandroid.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _uiState = MutableStateFlow<MainScreenUiState>(MainScreenUiState.Initial)
    val uiState: StateFlow<MainScreenUiState> = _uiState

    private val _listOfProducts = MutableStateFlow<List<ProductUIModel>?>(null)
    val listOfProducts: StateFlow<List<ProductUIModel>?> = _listOfProducts

    init {
        getProducts()
    }

    fun changeUiState(newState: MainScreenUiState) {
        _uiState.value = newState
    }

    private fun getProducts() {
        viewModelScope.launch {
            _uiState.value = MainScreenUiState.Loading

            when (val result = repository.getProducts()) {
                is ApiResult.Success -> {
                    _uiState.value = MainScreenUiState.Success(result.data)
                    _listOfProducts.value = result.data
                }

                is ApiResult.Error -> {
                    _uiState.value = MainScreenUiState.Error(result.error)
                }

                else -> {}
            }
        }
    }
}