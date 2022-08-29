package com.example.task1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.task1.databinding.ActivityMainBinding
import com.example.task1.model.Product
import com.example.task1.viewModel.ProductViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productViewModel: ProductViewModel
    private lateinit var productName: String
    private lateinit var productPrice: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewInit()
    }

    private fun viewInit() {
        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        binding.submitBtnId.setOnClickListener {

            productName = binding.productNameInputId.editText?.text.toString()
            productPrice = binding.productPriceInputId.editText?.text.toString()

            if (productName.isEmpty()) {
                binding.productNameInputId.error = "Field can not be empty"
                return@setOnClickListener
            }
            if (productPrice.isEmpty()) {
                binding.productPriceInputId.error = "Field can not be empty"
                return@setOnClickListener
            }

            lifecycleScope.launch {
                addDataToDatabase()
            }

        }

        binding.productNameInputId.editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isEmpty()) {
                    binding.productNameInputId.isErrorEnabled = true
                    binding.productNameInputId.error = "Field can not be empty"
                } else {
                    binding.productNameInputId.error = null
                    binding.productNameInputId.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })

        binding.productPriceInputId.editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isEmpty()) {
                    binding.productPriceInputId.isErrorEnabled = true
                    binding.productPriceInputId.error = "Field can not be empty"
                } else {
                    binding.productPriceInputId.error = null
                    binding.productPriceInputId.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })


    }

    private fun addDataToDatabase() {
        val sdf = SimpleDateFormat("MM d y")
        val currentDate = sdf.format(Date())

        val addProduct = Product(0, productName, productPrice, currentDate)
        productViewModel.addProduct(addProduct)

        Toast.makeText(this, "Successfully added!", Toast.LENGTH_SHORT).show()

        startActivity(Intent(applicationContext, ViewDataActivity::class.java))
    }
}