package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.register.*

class Done:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.done)

        lateinit var donebtn: Button

        donebtn.setOnClickListener{
            startActivity(Intent(this, Hcitem::class.java))
        }
    }
}