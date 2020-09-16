package com.example.assignment

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.payment.*
import kotlinx.android.synthetic.main.register.*
lateinit var selected:String
lateinit var selected2:String
class Payment:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment)

        val ewbtn = findViewById<Button>(R.id.ewalletbtn)
        val cdbtn = findViewById<Button>(R.id.cdCardbtn)
        val obbtn = findViewById<Button>(R.id.onlineBankingbtn)
        val codbtn = findViewById<Button>(R.id.cashOnDeliverybtn)
        val opaTextview = findViewById<TextView>(R.id.orderPaymentAmount)
        val daTextview = findViewById<TextView>(R.id.discountAmount)
        val taTextview = findViewById<TextView>(R.id.totalAmount)
        lateinit var nextPbtn: Button
        lateinit var backPbtn: Button

        nextPbtn.setOnClickListener{
            if(selected=="ew"||selected=="cd"||selected=="ob"||selected=="cod"){
                if(selected=="ew"){
                    selected2="e-wallet"
                }
                if(selected=="cd"){
                    selected2="credit/debit card"
                }
                if(selected=="ob"){
                    selected2="online banking"
                }
                if(selected=="cod"){
                    selected2="cash on delivery"
                }
                val user = FirebaseAuth.getInstance().currentUser
                val userEmail= user?.email
                val documentPath= "cart/$userEmail"
                var db: DocumentReference = FirebaseFirestore.getInstance().document(documentPath)

                val opaTextView2=opaTextview.text.toString()
                val daTextView2=daTextview.text.toString()
                val taTextView2=taTextview.text.toString()


                val y=HashMap<String, Any>()
                y.put("orderPaymentAmount",opaTextView2)
                y.put("discountAmount",daTextView2)
                y.put("totalAmount",taTextView2)
                y.put("paymentMethod",selected2)

                db.collection("paymentDetail").document("payment").set(y)
                startActivity(Intent(this, Done::class.java))
            }
            else{
                Toast.makeText(applicationContext, "Please select a payment method", Toast.LENGTH_SHORT).show()
            }

        }

        backPbtn.setOnClickListener{
            startActivity(Intent(this, DeliveryDetails::class.java))
        }

        ewbtn.setOnClickListener{
            ewbtn.setTextColor(Color.WHITE)
            ewbtn.setBackgroundResource(R.drawable.selected_button)
            cdbtn.setTextColor(Color.BLACK)
            cdbtn.setBackgroundResource(R.drawable.unselected_button)
            obbtn.setTextColor(Color.BLACK)
            obbtn.setBackgroundResource(R.drawable.unselected_button)
            codbtn.setTextColor(Color.BLACK)
            codbtn.setBackgroundResource(R.drawable.unselected_button)
            selected="ew"
            selectedbutton()
        }

        cdbtn.setOnClickListener{
            ewbtn.setTextColor(Color.BLACK)
            ewbtn.setBackgroundResource(R.drawable.unselected_button)
            cdbtn.setTextColor(Color.WHITE)
            cdbtn.setBackgroundResource(R.drawable.selected_button)
            obbtn.setTextColor(Color.BLACK)
            obbtn.setBackgroundResource(R.drawable.unselected_button)
            codbtn.setTextColor(Color.BLACK)
            codbtn.setBackgroundResource(R.drawable.unselected_button)
            selected="cd"
            selectedbutton()
        }

        obbtn.setOnClickListener{
            ewbtn.setTextColor(Color.BLACK)
            ewbtn.setBackgroundResource(R.drawable.unselected_button)
            cdbtn.setTextColor(Color.BLACK)
            cdbtn.setBackgroundResource(R.drawable.unselected_button)
            obbtn.setTextColor(Color.WHITE)
            obbtn.setBackgroundResource(R.drawable.selected_button)
            codbtn.setTextColor(Color.BLACK)
            codbtn.setBackgroundResource(R.drawable.unselected_button)
            selected="ob"
            selectedbutton()
        }

        codbtn.setOnClickListener{
            ewbtn.setTextColor(Color.BLACK)
            ewbtn.setBackgroundResource(R.drawable.unselected_button)
            cdbtn.setTextColor(Color.BLACK)
            cdbtn.setBackgroundResource(R.drawable.unselected_button)
            obbtn.setTextColor(Color.BLACK)
            obbtn.setBackgroundResource(R.drawable.unselected_button)
            codbtn.setTextColor(Color.WHITE)
            codbtn.setBackgroundResource(R.drawable.selected_button)
            selected="cod"
            selectedbutton()
        }
    }
    fun selectedbutton(){
        val a=findViewById<TextView>(R.id.orderPaymentAmount)

        if(selected=="ew"){
            a.setText(selected).toString()
        }
        if(selected=="cd"){
            a.setText(selected).toString()
        }
        if(selected=="ob"){
            a.setText(selected).toString()
        }
        if(selected=="cod"){
            a.setText(selected).toString()
        }
    }
}