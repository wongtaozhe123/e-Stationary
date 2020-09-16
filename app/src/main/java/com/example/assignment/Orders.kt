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
import kotlinx.android.synthetic.main.orders.btnNotifications
import kotlinx.android.synthetic.main.orders.btnOrders
import kotlinx.android.synthetic.main.orders.btnWishlist
import kotlinx.android.synthetic.main.orders.imgbtnAccount
import kotlinx.android.synthetic.main.orders.imgbtnCart
import kotlinx.android.synthetic.main.orders.imgbtnCustService
import kotlinx.android.synthetic.main.orders.imgbtnHome
import kotlinx.android.synthetic.main.orders.imgbtnSettings
import kotlinx.android.synthetic.main.orders.username
import kotlinx.android.synthetic.main.wishlist.*

class Orders:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.orders)

        getUserProfile()

        btnWishlist.setOnClickListener(){
            startActivity(Intent(this, Wishlist::class.java))
        }

        btnOrders.setOnClickListener(){
            startActivity(Intent(this, Orders::class.java))
        }

        btnNotifications.setOnClickListener(){
            startActivity(Intent(this, Notifications::class.java))
        }

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