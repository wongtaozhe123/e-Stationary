package com.example.assignment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import kotlinx.android.synthetic.main.delivery_details.*

class DeliveryDetails:AppCompatActivity() {

    lateinit var deliveryAddress: EditText
    lateinit var postalCode: EditText
    lateinit var city: EditText
    lateinit var states: String
    lateinit var additionalInformation: EditText
    lateinit var additionalInformation2: EditText
    lateinit var nextDDbtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delivery_details)

        val statespin = resources.getStringArray(R.array.state_choice)

        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, statespin
            )
            spinner.adapter = adapter
        }

        deliveryAddress = findViewById(R.id.deliveryAddress)
        postalCode = findViewById(R.id.postalCode)
        city = findViewById(R.id.city)
        states = spinner.selectedItem.toString()
        additionalInformation = findViewById(R.id.addInfor)
        additionalInformation2 = findViewById(R.id.addInfor2)
        nextDDbtn = findViewById(R.id.nextbtn)

        nextDDbtn.setOnClickListener{
            nextDD()
        }
        backbtn.setOnClickListener{
            startActivity(Intent(this, AddToCart::class.java))
        }

        }
    private fun nextDD() {
        val deliveryAddress2=deliveryAddress.text.toString()
        val postalCode2=postalCode.text.toString()
        val city2=city.text.toString()
        val additionalInformationB=additionalInformation.text.toString()
        val additionalInformation2B=additionalInformation2.text.toString()

        if(deliveryAddress2.trim().isEmpty()) {
            val dText = findViewById<EditText>(R.id.deliveryAddress);
            dText.setBackgroundResource(R.drawable.error_text_border)

            Toast.makeText(applicationContext, "Information in red is needed! ", Toast.LENGTH_SHORT).show()
        }else{
            val dText = findViewById<EditText>(R.id.deliveryAddress);
            dText.setBackgroundResource(R.drawable.edit_text_border_focus)
        }

        if(postalCode2.length!=5){
            val pText = findViewById<EditText>(R.id.postalCode);
            pText.setBackgroundResource(R.drawable.error_text_border)
            Toast.makeText(applicationContext, "Information in red is needed! ", Toast.LENGTH_SHORT).show()
        } else{
            val pText = findViewById<EditText>(R.id.postalCode);
            pText.setBackgroundResource(R.drawable.edit_text_border_focus)
        }

        if(city2.trim().isEmpty()) {
            val cText = findViewById<EditText>(R.id.city);
            cText.setBackgroundResource(R.drawable.error_text_border)
            Toast.makeText(applicationContext, "Information in red is needed! ", Toast.LENGTH_SHORT).show()
        }else{
            val cText = findViewById<EditText>(R.id.city);
            cText.setBackgroundResource(R.drawable.edit_text_border_focus)
        }
        if(city2.trim().isNotEmpty()&&postalCode2.length==5&&deliveryAddress2.trim().isNotEmpty()) {
            val user = FirebaseAuth.getInstance().currentUser
            val userEmail = user?.email
            val documentPath = "cart/$userEmail"
            var db: DocumentReference = FirebaseFirestore.getInstance().document(documentPath)

            val y = HashMap<String, Any>()
            y.put("deliveryAddress", deliveryAddress2)
            y.put("postalCode", postalCode2)
            y.put("city", city2)
            y.put("states", states)
            y.put("additionalInformation", additionalInformationB)
            y.put("additionalInformation2", additionalInformation2B)

            db.collection("deliveryDetail").document("address").set(y)

            val intent = Intent(this, Payment::class.java)
            startActivity(intent)
        }
        else{

        }
    }


}
