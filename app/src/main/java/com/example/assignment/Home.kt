package com.example.assignment

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import kotlinx.android.synthetic.main.wishlist.*
import kotlinx.android.synthetic.main.home.*
import kotlinx.android.synthetic.main.home.imgbtnAccount
import kotlinx.android.synthetic.main.home.imgbtnCart
import kotlinx.android.synthetic.main.home.imgbtnCustService
import kotlinx.android.synthetic.main.home.imgbtnHome
import kotlinx.android.synthetic.main.register.*

class Home:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
//        var preferences: SharedPreferences = getSharedPreferences("checkbox", MODE_PRIVATE)
//        var checkbox: String? = preferences.getString("remember","")
//        logout.setOnClickListener(){
//            var preferences: SharedPreferences = getSharedPreferences("checkbox", MODE_PRIVATE)
//            var editor: SharedPreferences.Editor = preferences.edit()
//            editor.putString("remember","false")
//            editor.apply()
//
//            finish()
//
//        }

        imgbtnHome.setOnClickListener{
            startActivity(Intent(this, Home::class.java))
        }

        imgbtnCustService.setOnClickListener{
            startActivity(Intent(this, CustomerService::class.java))
        }

        imgbtnCart.setOnClickListener{
            startActivity(Intent(this, AddToCart::class.java))
        }

        imgbtnAccount.setOnClickListener(){
            startActivity(Intent(this, Wishlist::class.java))
        }
    }
}