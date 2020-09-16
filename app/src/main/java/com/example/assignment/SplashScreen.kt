package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.HandlerCompat.postDelayed

class SplashScreen:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        },1000)

    }
}
