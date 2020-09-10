package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.customer_service.*
import kotlinx.android.synthetic.main.register.*
import kotlinx.android.synthetic.main.talk_to_us.*

class TalkToUs:AppCompatActivity() {
    private lateinit var db: DocumentReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db= FirebaseFirestore.getInstance().document("users/userDetail")
        setContentView(R.layout.talk_to_us)
        imgbtnSentTalkToUsMessage.setOnClickListener(){
            sentMessageToFirebase()
        }
    }
    fun sentMessageToFirebase(){
        val user = FirebaseAuth.getInstance().currentUser;
        if(user == null){
            Toast.makeText(this,"Please sign in with an account to liaise with us", Toast.LENGTH_SHORT).show()
        }
        else{
            val email=user.email
            if(txtSendMessage.text.toString().isEmpty()){
                Toast.makeText(this,"Please input your message before sent", Toast.LENGTH_SHORT).show()
            }
            else{
                val talkToUs = HashMap<String, Any>()
                talkToUs.put("Email",email.toString())
                talkToUs.put("Chat",txtSendMessage.text.toString())
                db.collection("$email").document("Messages")
                    .set(talkToUs)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(this,"Will get back to you soon", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener(){
                        e -> Toast.makeText(this,e.toString(), Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}