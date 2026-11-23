package com.example.roomdemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun MainScreen(viewModel: MainViewModel) {

    var productName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var searchName by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }

    val allProducts by viewModel.allProducts.observeAsState(emptyList())
    val searchResults by viewModel.searchResults.observeAsState(emptyList())

    val displayedProducts = if (isSearching) {
        searchResults
    } else {
        allProducts
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        TitleRow()

        CustomTextField(
            value = productName,
            onValueChange = { productName = it },
            label = "Название товара"
        )

        CustomTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = "Количество"
        )

        Button(
            onClick = {
                val count = quantity.toIntOrNull()

                if (productName.isNotBlank() && count != null) {
                    viewModel.insertProduct(Product(productName, count))
                    productName = ""
                    quantity = ""
                    isSearching = false
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Добавить товар")
        }

        CustomTextField(
            value = searchName,
            onValueChange = { searchName = it },
            label = "Поиск товара"
        )

        Button(
            onClick = {
                if (searchName.isNotBlank()) {
                    viewModel.findProduct(searchName)
                    isSearching = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Найти товар")
        }

        Button(
            onClick = {
                if (searchName.isNotBlank()) {
                    viewModel.deleteProduct(searchName)
                    searchName = ""
                    isSearching = false
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Удалить товар")
        }

        Button(
            onClick = {
                productName = ""
                quantity = ""
                searchName = ""
                isSearching = false
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Очистить поля")
        }

        Text("Список товаров")

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(displayedProducts) { product ->
                ProductRow(product = product)
            }
        }
    }
}
