package com.example.vkandroid.ui.screens.theme.screens.product

import androidx.lifecycle.ViewModel
import com.example.vkandroid.ProductUIModel
import com.example.vkandroid.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductScreenViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    fun getCurrentProduct(): ProductUIModel? {
        return repository.getCurrentProduct()
    }
}