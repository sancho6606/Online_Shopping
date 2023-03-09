package com.sancho.online_shopping.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sancho.online_shopping.R
import com.sancho.online_shopping.databinding.ActivityMainBinding
import com.sancho.online_shopping.model.CategoryModel
import com.sancho.online_shopping.view.adapters.AllProductsAdapter
import com.sancho.online_shopping.view.adapters.CategoryAdapter
import com.sancho.online_shopping.viewmodel.CategoryViewModel
import com.sancho.online_shopping.viewmodel.ProductViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var categoryViewModel: CategoryViewModel
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var productViewModel: ProductViewModel
    lateinit var allProductsAdapter: AllProductsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        categoryViewModel= ViewModelProvider(this@MainActivity).get(CategoryViewModel::class.java)
        productViewModel= ViewModelProvider(this@MainActivity).get(ProductViewModel::class.java)

        binding.apply {
            imageviewopencreatcategory.setOnClickListener {
                startActivity(Intent(this@MainActivity,MainActivityCreatCategory::class.java))
            }
            recyclerviewcategory.layoutManager=LinearLayoutManager(this@MainActivity,RecyclerView.HORIZONTAL,false)
            categoryViewModel.readallcategory().observe(this@MainActivity,{
                categoryAdapter=CategoryAdapter(this@MainActivity,it)
                recyclerviewcategory.adapter=categoryAdapter
            })

            recyclerviewallproduct.layoutManager=GridLayoutManager(this@MainActivity,2)

            productViewModel.readallproducts().observe(this@MainActivity,{
                it.shuffle()
                allProductsAdapter= AllProductsAdapter(this@MainActivity,it)
                recyclerviewallproduct.adapter=allProductsAdapter
            })
        }

    }
    fun categorychanged(categoryname:String){
        productViewModel.readeverycategory(categoryname).observe(this@MainActivity,{
            binding.textviewproduct.text=categoryname
            allProductsAdapter= AllProductsAdapter(this@MainActivity,it)
            binding.recyclerviewallproduct.adapter=allProductsAdapter
        })

    }
}