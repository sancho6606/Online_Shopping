package com.sancho.online_shopping.model.repositories

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
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

    var livedatasucces=MutableLiveData<Boolean>()
    var livedataprogress=MutableLiveData<Double>()

    var livedataallproducts=MutableLiveData<ArrayList<ProductModel>>()
    var arraylistallproducts=ArrayList<ProductModel>()
    fun addproduct(
        categoryname:String,
        name:String,
        uri:Uri,
        price:String,
        description:String,
    ){
        if (uri!=null){
            succes(true)
            val filereference: StorageReference = storageReference.child(System.currentTimeMillis().toString() + "." + System.currentTimeMillis().toString())
            filereference.putFile(uri)
                .addOnSuccessListener { filereference.downloadUrl.addOnSuccessListener {
                    var pushkey=databaseReference.push().key.toString()

                    val product=ProductModel(name,it.toString(),price,description,pushkey)
                    databaseReference.child(categoryname).child(pushkey).setValue(product)
                    databaseReferenceall.child(pushkey).setValue(product).addOnCompleteListener {
                        if (it.isSuccessful){
                            succes(false)
                        }
                    }

                } }
                .addOnProgressListener {
                    val progress: Double = 100.0 * it.getBytesTransferred() / it.getTotalByteCount()
                    livedataprogress.value=progress
                }
        }
    }

    //read all products from Firebase
    fun readallproductsfirebase():MutableLiveData<ArrayList<ProductModel>>{
        databaseReferenceall.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                arraylistallproducts.clear()
                for (datasnapshot:DataSnapshot in snapshot.children){
                    val productModel=datasnapshot.getValue(ProductModel::class.java)
                    arraylistallproducts.add(productModel!!)
                }

                livedataallproducts.value=arraylistallproducts
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        return livedataallproducts
    }

    fun readeverycategory(categoryname: String):MutableLiveData<ArrayList<ProductModel>>{
        databaseReference.child(categoryname).addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                arraylistallproducts.clear()
                for (datasnapshot:DataSnapshot in snapshot.children){
                    val productModel=datasnapshot.getValue(ProductModel::class.java)
                    arraylistallproducts.add(productModel!!)
                }

                livedataallproducts.value=arraylistallproducts
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        return livedataallproducts
    }

    fun succes(boolean: Boolean){
        livedatasucces.value=boolean
    }

}