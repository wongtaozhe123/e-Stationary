package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.hcitem.*



class Hcitem:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hcitem)


        val user = FirebaseAuth.getInstance().currentUser
        val userEmail= user?.email
        val documentPath= "cart/$userEmail"
        var db:DocumentReference = FirebaseFirestore.getInstance().document(documentPath)
        lateinit var imgbtnHome:ImageButton
        lateinit var imgbtnCustService:ImageButton
        lateinit var imgbtnCart:ImageButton
        lateinit var imgbtnAccount:ImageButton
        imgbtnHome = findViewById(R.id.imgbtnHome)
        imgbtnCustService = findViewById(R.id.imgbtnCustService)
        imgbtnCart = findViewById(R.id.imgbtnCart)
        imgbtnAccount = findViewById(R.id.imgbtnAccount)

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

        addtocart.setOnClickListener{
            val quantity = findViewById<EditText>(R.id.quantity)
            val quantity2=quantity.text.toString()
            val y=HashMap<String, Any>()
            y.put("colorFamily","Red")
            y.put("image","gs://estationary-4d8b6.appspot.com/monamimarker.jpg")
            y.put("itemID","I001")
            y.put("itemName","monami marker")
            y.put("price","2")
            y.put("quantity",quantity2)

            db.collection("cartDetail").document("monami marker").set(y)


        }
        addtocart2.setOnClickListener{
            val quantity = findViewById<EditText>(R.id.quantity2)
            val quantity2=quantity.text.toString()
            val u=HashMap<String, Any>()
            u.put("colorFamily","Black")
            u.put("image","gs://estationary-4d8b6.appspot.com/pencilcase.jpg")
            u.put("itemID","I002")
            u.put("itemName","pencil case")
            u.put("price","10")
            u.put("quantity",quantity2)

            db.collection("cartDetail").document("pencil case").set(u)

        }
        var toCartbtn: Button = findViewById(R.id.toCartbtn)
        toCartbtn.setOnClickListener {
            startActivity(Intent(this, AddToCart::class.java))
        }

    }

}

