package com.example.assignment

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.home.*
import kotlinx.android.synthetic.main.item_details.*
import kotlinx.android.synthetic.main.register.*
import kotlinx.android.synthetic.main.settings.*

class ItemDetails:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_details)

        addToCart.setOnClickListener{
            startActivity(Intent(this, AddToCart::class.java))
        }

        getItemInfo()
    }

    private fun getItemInfo() {
        val db = FirebaseFirestore.getInstance()

        db.collection("writingInstrument")
            .document("001")
            .get().addOnSuccessListener { result ->
            if (result != null) {
                itemNameS.setText("${result.getString("itemName")}")
                colorFamilyS.setText("${result.getString("colorFamily")}")
                priceS.setText("${result.getDouble("price")}")
            } else {
                Log.d(ContentValues.TAG, "No such document")
            }
        }

            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }
}