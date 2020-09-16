package com.example.assignment

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btnLoginTemp
import kotlinx.android.synthetic.main.activity_main.btnRegistrationTemp

class MainActivity : AppCompatActivity(){

    private lateinit var auth: FirebaseAuth;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth=FirebaseAuth.getInstance()
        var preferences: SharedPreferences = getSharedPreferences("checkbox", MODE_PRIVATE)
        var checkbox: String? = preferences.getString("remember","")
        if(checkbox.equals("true")){
            startActivity(Intent(this, Home::class.java))
            finish()
        }
        btnLogin.setOnClickListener() {
            if(txtUsername.text.trim().toString().isNotEmpty()&&txtPassword.text.trim().toString().isNotEmpty()){
                signInUser(txtUsername.text.toString(),txtPassword.text.toString())
            }
            else{
                Toast.makeText(this,"Please fill in your email and password", Toast.LENGTH_SHORT).show()
            }
        }
        btnLoginTemp.setOnClickListener(){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        btnRegistrationTemp.setOnClickListener(){
            startActivity(Intent(this,Register::class.java))
            finish()
        }
        chkboxKeepLogin.setOnCheckedChangeListener(){ compoundButton: CompoundButton, b: Boolean ->
            if(compoundButton.isChecked){
                var preferences: SharedPreferences = getSharedPreferences("checkbox", MODE_PRIVATE)
                var editor: SharedPreferences.Editor = preferences.edit()
                editor.putString("remember","true")
                editor.apply()
            }
            else{
                var preferences: SharedPreferences = getSharedPreferences("checkbox", MODE_PRIVATE)
                var editor: SharedPreferences.Editor = preferences.edit()
                editor.putString("remember","false")
                editor.apply()
            }
        }
        txtbtnForgotPassword.setOnClickListener(){
            startActivity(Intent(this, ForgotPassword::class.java))
        }
    }

    fun signInUser(email:String,password:String){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){
            task ->
            if(task.isSuccessful){
                startActivity(Intent(this, Home::class.java))
                finish()
            }
            else{
                Toast.makeText(this,"Error when signing in, please check your email and password", Toast.LENGTH_SHORT).show()
                Log.e("Log in error",task.exception.toString())
            }
        }
    }

}
