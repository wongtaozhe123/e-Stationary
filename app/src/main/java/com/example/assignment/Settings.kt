package com.example.assignment

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.settings.*


class Settings:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        var preferences: SharedPreferences = getSharedPreferences("checkbox", MODE_PRIVATE)
        var checkbox: String? = preferences.getString("remember","")

        getUserProfile()

        imgbtnBack.setOnClickListener(){
            startActivity(Intent(this, Wishlist::class.java))
        }

        btnLogout.setOnClickListener(){
            var preferences: SharedPreferences = getSharedPreferences("checkbox", MODE_PRIVATE)
            var editor: SharedPreferences.Editor = preferences.edit()
            editor.putString("remember","false")
            editor.apply()

            finish()

            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun getUserProfile() {
        val user = FirebaseAuth.getInstance().currentUser
        val email: String = user?.email.toString()
        val db = FirebaseFirestore.getInstance().document("users/userDetail")
        db.collection("userParticular").document("$email")
            .get()
            .addOnSuccessListener { result ->
                if (result != null) {
                    displayName.setText("${result.getString("Username")}")
                    displayEmail.setText("${result.getString("Email")}")
                    displayPhoneNo.setText("${result.getString("phoneNumber")}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
}