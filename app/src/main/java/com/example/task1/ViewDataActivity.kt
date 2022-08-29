package com.example.task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task1.adapter.ProductListAdapter
import com.example.task1.databinding.ActivityViewDataBinding
import com.example.task1.viewModel.ProductViewModel

class ViewDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewDataBinding
    private lateinit var adapter: ProductListAdapter
    private lateinit var productViewModel: ProductViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityViewDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewInit()

    }

    private fun viewInit() {

        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        adapter = ProductListAdapter(this)
        binding.tableDataRvId.layoutManager = LinearLayoutManager(this)
        binding.tableDataRvId.adapter = adapter

        productViewModel.readAllData.observe(this) { product ->
            adapter.setData(product)
        }

        productViewModel.getProductPrice()
        productViewModel.getAllPrice.observe(this){
            binding.totalValeTvId.text=it.toString()
        }
    }
}