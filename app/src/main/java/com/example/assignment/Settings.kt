package com.example.assignment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.settings.*


class Settings:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        var preferences: SharedPreferences = getSharedPreferences("checkbox", MODE_PRIVATE)
        var checkbox: String? = preferences.getString("remember","")

        getUserProfile()

        btnLogout.setOnClickListener(){
            var preferences: SharedPreferences = getSharedPreferences("checkbox", MODE_PRIVATE)
            var editor: SharedPreferences.Editor = preferences.edit()
            editor.putString("remember","false")
            editor.apply()

            finish()
        }
    }

    private fun getUserProfile() {
        // [START get_user_profile]
        val user = FirebaseAuth.getInstance().currentUser

        user?.let {
            // Name, email address, and profile photo Url
            val uid = user.uid
            displayName.setText(user.displayName)
            displayEmail.setText(user.email)
            displayPhoneNo.setText(user.phoneNumber)
        }
        // [END get_user_profile]
    }
}