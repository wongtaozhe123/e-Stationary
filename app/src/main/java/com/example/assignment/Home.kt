package com.example.assignment

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import kotlinx.android.synthetic.main.home.*
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
    }
}