package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.complain.*

class Complain:AppCompatActivity() {
    private lateinit var db: DocumentReference
    override fun onCreate(savedInstanceState: Bundle?) {
        var category: String = "Null"
        db=FirebaseFirestore.getInstance().document("users/userDetail")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.complain)
        btnLateDelivery.setOnClickListener(){
            category = "late Delivery"
        }
        btnFaultyProducts.setOnClickListener(){
            category = "Faulty Products"
        }
        btnUnreasonablePrice.setOnClickListener(){
            category = "Unreasonable Price"
        }
        btnPoorHospitality.setOnClickListener(){
            category = "Poor Hospitality"
        }
        btncomplainOthers.setOnClickListener(){
            category = "Others"
        }
        btnSendComplain.setOnClickListener(){
            passComplainToFirebase(category)
        }
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
    }
    fun passComplainToFirebase(category: String){
        if(category == "Null" || txtOrderNo.text.toString().isEmpty() || txtComplainDescription.text.toString().isEmpty()){
            Toast.makeText(this,"Please fill in every column including select complaint category", Toast.LENGTH_SHORT).show()
        }
        else{
            val user = FirebaseAuth.getInstance().currentUser;
            if(user == null){
                Toast.makeText(this,"Please sign in before doing any complains", Toast.LENGTH_SHORT).show()
            }
            else{
                val email = user.email
                val complain = HashMap<String, Any>()
                complain.put("complain Categories", category)
                complain.put("Order refering", txtOrderNo.text.toString())
                complain.put("Description",txtComplainDescription.text.toString())
                complain.put("User email", email.toString())
                db.collection("$email").document("Complains")
                    .set(complain)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(this,"Thank you for your feedback, sorry for the inconvenience caused", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, CustomerService::class.java))
                    }
                    .addOnFailureListener{
                        e -> Toast.makeText(this,e.toString(), Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}