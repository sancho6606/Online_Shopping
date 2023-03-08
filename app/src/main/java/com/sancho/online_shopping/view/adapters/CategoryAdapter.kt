package com.sancho.online_shopping.view.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sancho.online_shopping.databinding.RecyclerviewItemCategoryBinding
import com.sancho.online_shopping.model.CategoryModel
import com.sancho.online_shopping.view.MainActivityCreatProduct

class CategoryAdapter constructor(
    val context: Context,
    val arrayList: ArrayList<CategoryModel>,

):RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){

    class CategoryViewHolder(val binding:RecyclerviewItemCategoryBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view=RecyclerviewItemCategoryBinding.inflate(LayoutInflater.from(context),parent,false)
        return CategoryViewHolder(view)
    }
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.apply {
            textviewcategorytext.text=arrayList.get(position).name
            Glide.with(context).load(arrayList.get(position).imageurl).into(imageviewcategoryimage)

            linearlaycategory.setOnLongClickListener {
                val intent=Intent(context,MainActivityCreatProduct::class.java)
                intent.putExtra("name",arrayList.get(position).name)
                intent.putExtra("image",arrayList.get(position).imageurl)
                context.startActivity(intent)
                return@setOnLongClickListener true
            }
        }
    }
    override fun getItemCount(): Int=arrayList.size



}