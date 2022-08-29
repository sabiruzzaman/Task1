package com.example.task1.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.task1.data.ProductDatabase
import com.example.task1.model.Product
import com.example.task1.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.log

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Product>>
    val getAllPrice = MutableLiveData<Int>()

    private val repository: ProductRepository

    init {
        val productDao = ProductDatabase.getDatabase(application).productDao
        repository = ProductRepository(productDao)
        readAllData = repository.readAllData

    }

    fun addProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProduct(product)
        }
    }


    fun getProductPrice() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllPrice.postValue(repository.getProductPrice())

        }
    }
}
