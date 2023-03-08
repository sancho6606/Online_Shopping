package com.sancho.online_shopping.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sancho.online_shopping.model.ProductModel
import com.sancho.online_shopping.model.repositories.RepositoryProduct

class ProductViewModel constructor(
    val repositoryProduct: RepositoryProduct= RepositoryProduct()
):ViewModel() {

    fun addnewproduct(
        categoryname:String,
        name:String,
        uri: Uri,
        price:String,
        description:String,
    ){
        repositoryProduct.addproduct(categoryname, name, uri, price, description)
    }

    fun readallproducts():MutableLiveData<ArrayList<ProductModel>>{
       return repositoryProduct.readallproductsfirebase()
    }
    fun readeverycategory(categoryname: String):MutableLiveData<ArrayList<ProductModel>>{
        return repositoryProduct.readeverycategory(categoryname)
    }
    fun uploadproductprogress(): MutableLiveData<Double> {
        return repositoryProduct.livedataprogress
    }
    fun uploadsucces(): MutableLiveData<Boolean> {
        return repositoryProduct.livedatasucces
    }

}