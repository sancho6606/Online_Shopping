package com.sancho.online_shopping.view.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sancho.online_shopping.databinding.RecyclerviewItemCategoryBinding
import com.sancho.online_shopping.databinding.RecyclerviewItemallproductsBinding
import com.sancho.online_shopping.model.ProductModel
import com.sancho.online_shopping.view.MainActivityProductInformation

class AllProductsAdapter constructor(
    val context: Context,
    val arrayList: ArrayList<ProductModel>
):RecyclerView.Adapter<AllProductsAdapter.AllProductsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllProductsViewHolder {
        val view=RecyclerviewItemallproductsBinding.inflate(LayoutInflater.from(context),parent,false)
        return AllProductsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllProductsViewHolder, position: Int) {
        holder.binding.apply {
            textviewproductname.text=arrayList.get(position).name
            textviewproductdescription.text=arrayList.get(position).description
            textviewproductprice.text=arrayList.get(position).price+" $"
            Glide.with(context).load(arrayList.get(position).imageurl).into(imageviewproductimage)
            linearlayproduct.setOnClickListener {
                val intent=Intent(context,MainActivityProductInformation::class.java)
                intent.putExtra("product",arrayList.get(position))
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int =arrayList.size

    class AllProductsViewHolder(val binding: RecyclerviewItemallproductsBinding):RecyclerView.ViewHolder(binding.root)
}