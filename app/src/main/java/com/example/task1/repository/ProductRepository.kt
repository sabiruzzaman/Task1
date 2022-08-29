package com.example.task1.repository

import androidx.lifecycle.LiveData
import com.example.task1.data.ProductDao
import com.example.task1.model.Product

class ProductRepository(private val productDao: ProductDao) {

    val readAllData: LiveData<List<Product>> = productDao.getAllProduct()

    suspend fun addProduct(product: Product) {
        productDao.addProduct(product)
    }

     fun getProductPrice()= productDao.getAllProductPrice()
}