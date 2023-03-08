package com.sancho.online_shopping.model.repositories

import android.net.Uri
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.sancho.online_shopping.model.CategoryModel
import com.sancho.online_shopping.model.ProductModel
import com.sancho.online_shopping.utilits.Constants

class RepositoryProduct constructor(
    var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.PRODUCTS),
    var databaseReferenceall: DatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.ALLPRODUCTS),
    var storageReference: StorageReference = FirebaseStorage.getInstance().getReference(Constants.PRODUCTS)
){

    fun addproduct(
        categoryname:String,
        name:String,
        uri:Uri,
        price:String,
        description:String,
    ){
        if (uri!=null){
            val filereference: StorageReference = storageReference.child(System.currentTimeMillis().toString() + "." + System.currentTimeMillis().toString())
            filereference.putFile(uri)
                .addOnSuccessListener { filereference.downloadUrl.addOnSuccessListener {
                    var pushkey=databaseReference.push().key.toString()

                    val product=ProductModel(name,it.toString(),price,description,pushkey)
                    databaseReference.child(categoryname).child(pushkey).setValue(product)
                    databaseReferenceall.child(pushkey).setValue(product)

                } }
                .addOnProgressListener {
                    val progress: Double = 100.0 * it.getBytesTransferred() / it.getTotalByteCount()

                }
        }
    }

}