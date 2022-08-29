package com.example.task1.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.task1.model.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addProduct(product: Product)

    @Query("SELECT * FROM product_table ORDER BY id ASC")
     fun getAllProduct(): LiveData<List<Product>>

    @Query("SELECT SUM(productPrice) FROM product_table ORDER BY id ASC")
    fun getAllProductPrice(): Int
}