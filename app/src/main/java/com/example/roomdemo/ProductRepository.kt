package com.example.roomdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductRepository(private val productDao: ProductDao) {

    val allProducts: LiveData<List<Product>> =
        productDao.getAllProducts()

    private val _searchResults = MutableLiveData<List<Product>>()
    val searchResults: LiveData<List<Product>> = _searchResults

    fun insertProduct(product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            productDao.insertProduct(product)
        }
    }

    fun findProduct(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            _searchResults.postValue(productDao.findProduct(name))
        }
    }

    fun deleteProduct(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            productDao.deleteProduct(name)
        }
    }
}
