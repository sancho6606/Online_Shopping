package com.sancho.online_shopping.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sancho.online_shopping.model.CategoryModel
import com.sancho.online_shopping.model.repositories.RepositoryCategory

class CategoryViewModel constructor(
    val repositoryCategory: RepositoryCategory= RepositoryCategory()
):ViewModel() {

    fun uploadnewcategory(name:String,uri: Uri){
        repositoryCategory.uploadcategory(name,uri)
    }
    fun uploadsucces():MutableLiveData<Boolean>{
        return repositoryCategory.livedatasucces
    }
    fun uploadprogress():MutableLiveData<Double>{
        return repositoryCategory.livedataprogress
    }
    fun readallcategory():MutableLiveData<ArrayList<CategoryModel>>{
        return repositoryCategory.readalldata()
    }
}