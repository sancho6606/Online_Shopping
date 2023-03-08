package com.sancho.online_shopping.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
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

}