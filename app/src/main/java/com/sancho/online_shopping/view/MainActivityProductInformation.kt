package com.sancho.online_shopping.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.sancho.online_shopping.R
import com.sancho.online_shopping.databinding.ActivityMainProductInformationBinding
import com.sancho.online_shopping.model.ProductModel

class MainActivityProductInformation : AppCompatActivity() {
    lateinit var binding: ActivityMainProductInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainProductInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.apply {
            var product:ProductModel=intent.getSerializableExtra("product") as ProductModel
            Glide.with(this@MainActivityProductInformation).load(product.imageurl).into(imageviewproduct)
            textviewproductnameinfo.text="${product!!.name}"
            textviewproductdescriptioninfo.text="${product!!.description}"
            textviewproductpriceinfo.text="${product!!.price} $"
        }
    }
}