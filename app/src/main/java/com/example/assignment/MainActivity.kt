package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    var x1:Float = 0.0F
    var x2:Float = 0.0F
    var y1:Float = 0.0F
    var y2:Float = 0.0F
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delivery_details)
        auth=FirebaseAuth.getInstance()

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
        }
        btnRegistrationTemp.setOnClickListener(){
            startActivity(Intent(this,Register::class.java))
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                x1=event.getX()
            }
            MotionEvent.ACTION_UP -> {
                x2=event.getX()
                if(x1>x2){
                    startActivity(Intent(this, Register::class.java))
                }
            }
        }
        return false
    }

    override fun onStart() {
        super.onStart()
        val user=auth.currentUser;
        if(user != null){
            val intent = Intent(this,Home::class.java)
            startActivity(intent)
        }
    }
    fun signInUser(email:String,password:String){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){
            task ->
            if(task.isSuccessful){
                startActivity(Intent(this, Home::class.java))
            }
            else{
                Toast.makeText(this,"Error when signing in, please check your email and password", Toast.LENGTH_SHORT).show()
                Log.e("Log in error",task.exception.toString())
            }
        }
    }

}
