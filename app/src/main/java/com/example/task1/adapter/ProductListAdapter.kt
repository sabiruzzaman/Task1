package com.example.task1.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.R
import com.example.task1.databinding.ItemTableBinding
import com.example.task1.model.Product


class ProductListAdapter(private val context: Context) :
    RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    private var productList = emptyList<Product>()

    class ProductListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemTableBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_table, parent, false)
        return ProductListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {

        val product = productList[position]
        with(holder.binding) {

            productNameTvId.text = product.productName
            productPriceTvId.text = product.productPrice
            dateTvId.text = product.date

        }

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(product: List<Product>) {
        this.productList = product
        notifyDataSetChanged()
    }

}


