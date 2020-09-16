package com.example.assignment

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.notifications.*
import kotlinx.android.synthetic.main.notifications.btnNotifications
import kotlinx.android.synthetic.main.notifications.btnOrders
import kotlinx.android.synthetic.main.notifications.btnWishlist
import kotlinx.android.synthetic.main.notifications.imgbtnAccount
import kotlinx.android.synthetic.main.notifications.imgbtnCart
import kotlinx.android.synthetic.main.notifications.imgbtnCustService
import kotlinx.android.synthetic.main.notifications.imgbtnHome
import kotlinx.android.synthetic.main.notifications.imgbtnSettings
import kotlinx.android.synthetic.main.notifications.username
import kotlinx.android.synthetic.main.wishlist.*

class Notifications:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notifications)

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