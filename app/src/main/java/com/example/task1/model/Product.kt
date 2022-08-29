package com.example.task1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val productName: String,
    val productPrice: String,
    val date: String
)