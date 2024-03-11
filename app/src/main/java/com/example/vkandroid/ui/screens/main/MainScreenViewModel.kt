package com.example.vkandroid.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkandroid.Constants
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
) : ViewModel() {
    private val _productsUiState = MutableStateFlow<ProductsUiState>(ProductsUiState.Initial)
    val productsUiState: StateFlow<ProductsUiState> = _productsUiState

    private val _categoriesUiState = MutableStateFlow<CategoriesUiState>(CategoriesUiState.Initial)
    val categoriesUiState: StateFlow<CategoriesUiState> = _categoriesUiState

    private val _searchUiState = MutableStateFlow<SearchUiState>(SearchUiState.Initial)
    val searchUiState: StateFlow<SearchUiState> = _searchUiState

    private val _listOfProducts = MutableStateFlow<MutableList<ProductUIModel>?>(null)
    val listOfProducts: StateFlow<List<ProductUIModel>?> = _listOfProducts

    private val _listOfSearchedProducts = MutableStateFlow<MutableList<ProductUIModel>?>(null)
    val listOfSearchedProducts: StateFlow<List<ProductUIModel>?> = _listOfSearchedProducts

    private val _listOfCategories = MutableStateFlow<List<String>?>(null)
    val listOfCategories: StateFlow<List<String>?> = _listOfCategories

    private val _currentCategory = MutableStateFlow<String?>(null)
    val currentCategory: StateFlow<String?> = _currentCategory

    private var currentPage = 0

    init {
        getAllCategories()
        getAllProducts()
    }

    fun changeProductsUiState(newState: ProductsUiState) {
        _productsUiState.value = newState
    }

    fun changeCategoriesUiState(newState: CategoriesUiState) {
        _categoriesUiState.value = newState
    }

    fun changeSearchUiState(newState: SearchUiState) {
        _searchUiState.value = newState
    }

    fun getAllProducts() {
        viewModelScope.launch {
            _productsUiState.value = ProductsUiState.Loading

            when (val result = repository.getProducts()) {
                is ApiResult.Success -> {
                    _productsUiState.value = ProductsUiState.Success(result.data)
                    _listOfProducts.value = result.data.toMutableList()
                    currentPage++
                }

                is ApiResult.Error -> {
                    _productsUiState.value = ProductsUiState.Error(result.error)
                }
            }
        }
    }

    fun getMoreProducts(category: String? = null) {
        viewModelScope.launch {
            _productsUiState.value = ProductsUiState.Loading

            when (val result = repository.getProducts(
                skip = currentPage * Constants.LIMIT_PRODUCTS,
                category = category
            )) {
                is ApiResult.Success -> {
                    _productsUiState.value = ProductsUiState.Success(result.data)

                    if (category != null) {
                        _listOfSearchedProducts.value?.addAll(result.data)
                    } else {
                        _listOfProducts.value?.addAll(result.data)
                    }

                    if (result.data.isNotEmpty()) {
                        currentPage++
                    }
                }

                is ApiResult.Error -> {
                    _productsUiState.value = ProductsUiState.Error(result.error)
                }
            }
        }
    }

    fun searchProducts(query: String) {
        viewModelScope.launch {
            _searchUiState.value = SearchUiState.Loading

            when (val result = repository.getProducts(query = query)) {
                is ApiResult.Success -> {
                    _searchUiState.value = SearchUiState.Success(result.data)
                    _listOfSearchedProducts.value = result.data.toMutableList()
                }

                is ApiResult.Error -> {
                    _searchUiState.value = SearchUiState.Error(result.error)
                    _listOfSearchedProducts.value = mutableListOf()
                }
            }
        }
    }

    fun getAllCategories() {
        viewModelScope.launch {
            _categoriesUiState.value = CategoriesUiState.Loading

            when (val result = repository.getCategories()) {
                is ApiResult.Success -> {
                    _categoriesUiState.value = CategoriesUiState.Success(result.data)
                    _listOfCategories.value = result.data
                }

                is ApiResult.Error -> {
                    _categoriesUiState.value = CategoriesUiState.Error(result.error)
                }
            }
        }
    }

    fun searchProductsByCategory(category: String) {
        currentPage = 0
        viewModelScope.launch {
            _categoriesUiState.value = CategoriesUiState.Loading

            when (val result = repository.getProductsByCategory(category = category)) {
                is ApiResult.Success -> {
                    _categoriesUiState.value = CategoriesUiState.Success(result.data)
                    _currentCategory.value = category
                    _listOfSearchedProducts.value = result.data.toMutableList()

                    if (result.data.isNotEmpty()) {
                        currentPage++
                    }

                }

                is ApiResult.Error -> {
                    _categoriesUiState.value = CategoriesUiState.Error(result.error)
                }
            }
        }
    }

    fun setCurrentProduct(newProduct: ProductUIModel) {
        repository.setCurrentProduct(newProduct)
    }

    fun clearListOfSearchedProducts() {
        _listOfSearchedProducts.value = null
    }

    fun clearCurrentCategory() {
        _currentCategory.value = null
    }
}