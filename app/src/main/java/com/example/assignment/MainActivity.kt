package com.example.assignment

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth=FirebaseAuth.getInstance()
//        val adapter=tzViewPagerAdapter(supportFragmentManager)
//        adapter.addFragment(loginFragment(),title = "login")
//        adapter.addFragment(registerFragment(),title = "register")
//        tzSwipeViewPager.adapter=adapter
//        tzLoginRegister.setupWithViewPager(tzSwipeViewPager)
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
//    class tzViewPagerAdapter(manager:FragmentManager):FragmentPagerAdapter(manager){
//        private val fragmentList:MutableList<Fragment> = ArrayList()
//        private val titleList:MutableList<String> = ArrayList()
//        override fun getItem(position: Int): Fragment {
//           when(position){
//               0 -> {return loginFragment()}
//               1 -> {return registerFragment()}
//               else -> {return loginFragment()}
//           }
//        }
//
//        override fun getCount(): Int {
//           return fragmentList.size
//        }
//        fun addFragment(fragment: Fragment, title:String){
//           fragmentList.add(fragment)
//           titleList.add(title)
//        }
//
//        override fun getPageTitle(position: Int): CharSequence? {
//           return titleList[position]
//        }
//    }
//    override fun onStart() {
//        super.onStart()
//        val user=auth.currentUser;
//        if(user != null){
//            val intent = Intent(this,Home::class.java)
//            startActivity(intent)
//        }
//    }
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
