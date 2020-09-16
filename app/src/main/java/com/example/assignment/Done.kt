package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import kotlinx.android.synthetic.main.register.*

class Done:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.done)

        var donebtn: Button = findViewById(R.id.donebtn)
        lateinit var imgbtnHome:ImageButton
        lateinit var imgbtnCustService:ImageButton
        lateinit var imgbtnCart:ImageButton
        lateinit var imgbtnAccount:ImageButton
        imgbtnHome = findViewById(R.id.imgbtnHome)
        imgbtnCustService = findViewById(R.id.imgbtnCustService)
        imgbtnCart = findViewById(R.id.imgbtnCart)
        imgbtnAccount = findViewById(R.id.imgbtnAccount)

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

        donebtn.setOnClickListener{
            startActivity(Intent(this, Home::class.java))
        }

    }
}