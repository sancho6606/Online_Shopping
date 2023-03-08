package com.sancho.online_shopping.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.sancho.online_shopping.R
import com.sancho.online_shopping.databinding.ActivityMainCreatProductBinding
import com.sancho.online_shopping.viewmodel.ProductViewModel

class MainActivityCreatProduct : AppCompatActivity() {
    lateinit var binding: ActivityMainCreatProductBinding
    var categoryname:String?=null
    var categoryimage:String?=null
    var imageuri:Uri?=null
    lateinit var productViewModel: ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainCreatProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        productViewModel=ViewModelProvider(this@MainActivityCreatProduct).get(ProductViewModel::class.java)
        categoryname=intent.getStringExtra("name")
        categoryimage=intent.getStringExtra("image")
        binding.apply {
            Glide.with(this@MainActivityCreatProduct).load(categoryimage).into(circleimageviewcateryimageinproduct)
            textviewcategoryinproduct.text=categoryname
            imageviewopengalleryproduct.setOnClickListener {
                openFileChooser()
            }
            buttonaddproducts.setOnClickListener {
                productViewModel.addnewproduct(
                    categoryname!!,
                    edittextproductname.text.toString(),
                    imageuri!!,
                    edittextproductprice.text.toString(),
                    edittextproductdescription.text.toString(),
                )
            }
            productViewModel.uploadsucces().observe(this@MainActivityCreatProduct,{
                if (it){
                    showprogress()
                }else{
                    hideprogress()
                }
            })
            productViewModel.uploadproductprogress().observe(this@MainActivityCreatProduct,{
                textviewprogressproduct.text="${it.toInt()}%"
                progressBarhorizontalproduct.progress=it.toInt()
            })


        }

    }
    //Open Gallery
    fun openFileChooser() {
        getContent.launch("image/*")
    }
    //Open Gallery and Set image to imageview
    val getContent = registerForActivityResult(ActivityResultContracts.GetContent())  { uri: Uri? ->
        binding.imageviewopengalleryproduct.setImageURI(uri)
        if (uri!=null){
            imageuri=uri
            binding.buttonaddproducts.isEnabled=true
        }
    }

    //show progress hide progress
    fun showprogress(){
        binding.apply {
            progressBarhorizontalproduct.visibility=View.VISIBLE
            textviewprogressproduct.visibility=View.VISIBLE
        }
    }
    fun hideprogress(){
        binding.apply {
            progressBarhorizontalproduct.visibility=View.GONE
            textviewprogressproduct.visibility=View.GONE
        }
    }











}