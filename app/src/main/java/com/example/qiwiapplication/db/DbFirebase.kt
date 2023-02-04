package com.example.qiwiapplication.db

import android.util.JsonToken
import android.util.Log
import com.example.qiwiapplication.utils.SharedPref
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging

object DbFirebase {

    private val database = Firebase.database.reference


    fun addTokenDB(token: String){
        database.child("users").child(SharedPref.getPersonId()).get().addOnSuccessListener {
            var result = false
            for (postSnapshot in it.children) {
                if(token == postSnapshot.value.toString()) result = true
            }
            if(!result){
                val key = database.child("users").child(SharedPref.getPersonId()).push().key
                if (key != null) {
                    database.child("users").child(SharedPref.getPersonId()).child(key)
                        .setValue(token)
                    Log.e("succes", "token added")
                }
            }
        }.addOnFailureListener{ }
    }



//    fun removeTokenDB(){
//        val token = FirebaseMessaging.getInstance().getToken()
//        database.child("users").child(SharedPref.getPersonId()).get().addOnSuccessListener {
//            for (postSnapshot in it.children) {
//                if(token == postSnapshot.child("result").value) {
//                    val ref = database.child("users").child(SharedPref.getPersonId()).child(postSnapshot.key.toString())
//                    database.child("users").child(SharedPref.getPersonId()).removeValue(ref)
//                }
//            }
//        }.addOnFailureListener{ }
//    }



}