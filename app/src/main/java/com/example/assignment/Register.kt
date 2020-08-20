package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.register.*

public class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        btnRegister.setOnClickListener{
            Toast.makeText(this,"Thank you for signing up with E-Stationary",Toast.LENGTH_SHORT).show()
            val intent = Intent(this,AccountInfo::class.java)
            startActivity(intent)
        }
    }

}
