package com.example.assignment

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.orders.*

class Orders: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.orders)

        getUserProfile()

        btnOrders.setTextColor(Color.parseColor("#FF000000"))
        btnOrders.setBackgroundColor(Color.parseColor("#FFFFFFFF"))

        imgbtnHome.setOnClickListener() {
            startActivity(Intent(this, Home::class.java))
        }

        imgbtnCustService.setOnClickListener() {
            startActivity(Intent(this, CustomerService::class.java))
        }

        imgbtnCart.setOnClickListener() {
            startActivity(Intent(this, AddToCart::class.java))
        }

        imgbtnAccount.setOnClickListener() {
            startActivity(Intent(this, Wishlist::class.java))
        }

        imgbtnSettings.setOnClickListener(){
            startActivity(Intent(this, Settings::class.java))
        }

    }

    private fun getUserProfile() {
        //display username
        val user = FirebaseAuth.getInstance().currentUser
        val email: String = user?.email.toString()
        val db = FirebaseFirestore.getInstance().document("users/userDetail")
        db.collection("userParticular").document("$email")
            .get()
            .addOnSuccessListener { result ->
                if (result != null) {
                    username.setText("${result.getString("Username")}")
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }
}