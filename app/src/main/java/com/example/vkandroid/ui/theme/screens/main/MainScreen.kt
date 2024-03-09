package com.example.vkandroid.ui.theme.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.vkandroid.ProductUIModel
import com.example.vkandroid.ui.theme.screens.ProductItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val products by viewModel.listOfProducts.collectAsState()

    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var showSearchBar by remember { mutableStateOf(false) }

    when (uiState) {
        is MainScreenUiState.Success<*> -> {
            viewModel.changeUiState(MainScreenUiState.Initial)
        }

        is MainScreenUiState.Error -> {}

        else -> {}
    }

    Scaffold(
        topBar = {
            if (showSearchBar) {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                    query = text,
                    onQueryChange = {
                        text = it
                    },
                    onSearch = {

                    },
                    active = active,
                    onActiveChange = {
                        active = it
                        if (!active) {
                            showSearchBar = false
                        }
                    }
                ) {
                    Text(text = "heelo")
                }
            } else {
                TopAppBar(
                    title = {
                        Text(
                            text = "Главная",
                            fontWeight = FontWeight.Bold
                        )
                    },
                    actions = {
                        IconButton(onClick = { showSearchBar = true }) {
                            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            state = rememberLazyGridState(),
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(innerPadding),
            contentPadding = PaddingValues(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            itemsIndexed(
                items = products ?: emptyList(),
                key = { _: Int, item: ProductUIModel ->
                    item.hashCode()
                }
            ) { _, item ->
                ProductItem(productUIModel = item)
            }
        }
    }
}