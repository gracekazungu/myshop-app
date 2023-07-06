package com.example.myshop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myshop.databinding.ProductlistBinding
import com.squareup.picasso.Picasso

class ProductAdapter (var productList:List<Product>): RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding=ProductlistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ProductViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct=productList[position]
        val binding=holder.binding
        binding.tvtitle.text=currentProduct.title
        binding.tvid.text=currentProduct.id.toString()
        binding.tvdescription.text=currentProduct.description
        binding.tvprice.text=currentProduct.price.toString()
        binding.tvrating.text=currentProduct.rating.toString()
        binding.tvstock.text=currentProduct.stock.toString()
        binding.tvcategory.text=currentProduct.category
        Picasso
            .get()
            .load(currentProduct.thumbnail)
            .resize(80,80)
            .centerCrop()
            .into(binding.tvthumbnail)

    }
    override fun getItemCount(): Int {
        return productList.size
    }
}
class ProductViewHolder(val binding: ProductlistBinding):RecyclerView.ViewHolder(binding.root)
//    fun bind(){
//
//    }


