package com.sancho.online_shopping.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.sancho.online_shopping.R
import com.sancho.online_shopping.databinding.ActivityMainCreatCategoryBinding
import com.sancho.online_shopping.viewmodel.CategoryViewModel

class MainActivityCreatCategory : AppCompatActivity() {
    lateinit var binding : ActivityMainCreatCategoryBinding
    var imageuri:Uri?=null
    lateinit var categoryViewModel: CategoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainCreatCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        categoryViewModel=ViewModelProvider(this@MainActivityCreatCategory).get(CategoryViewModel::class.java)

        binding.apply {
            imageviewcreatimagecategory.setOnClickListener {
                openFileChooser()
            }
            binding.buttoncreatcategory.setOnClickListener {
                if (imageuri!=null){
                    categoryViewModel.uploadnewcategory(edittextcategoryname.text.toString(),imageuri!!)
                }else{
                    Toast.makeText(this@MainActivityCreatCategory,"Select a Image for Category", Toast.LENGTH_SHORT).show()
                }

            }
        }

        //progressbarshowhide
        categoryViewModel.uploadsucces().observe(this@MainActivityCreatCategory,{
            if (it){
                showprogressbar()
            }else{
                hideprogressbar()
            }
        })
        categoryViewModel.uploadprogress().observe(this@MainActivityCreatCategory,{
            binding.apply {
                progressBarhorizontal.progress=it.toInt()
                textviewprogress.text="${it.toInt()} % "
            }
        })

        //progressbarshowhide

    }
    //Show progressbar hide progressbar
    fun showprogressbar(){
        binding.apply {
            progressBarhorizontal.visibility=View.VISIBLE
            textviewprogress.visibility=View.VISIBLE
        }
    }
    fun hideprogressbar(){
        binding.apply {
            progressBarhorizontal.visibility=View.GONE
            textviewprogress.visibility=View.GONE
        }
    }

    //Open Gallery
    fun openFileChooser() {
        getContent.launch("image/*")
    }
    //Open Gallery and Set image to imageview
    val getContent = registerForActivityResult(ActivityResultContracts.GetContent())  { uri: Uri? ->
        binding.imageviewcreatimagecategory.setImageURI(uri)
        if (uri!=null){
            imageuri=uri
            binding.buttoncreatcategory.isEnabled=true
        }
    }
}