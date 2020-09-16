package com.example.assignment

import android.content.Intent
import android.net.Uri
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
import java.lang.Exception

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
                val mail = Intent(Intent.ACTION_SEND)
                mail.data = Uri.parse("mailto:")
                mail.type = "text/plain"
                mail.putExtra(Intent.EXTRA_EMAIL, arrayOf("wongtaozhelgd@gmail.com"))
                mail.putExtra(Intent.EXTRA_SUBJECT,"talk to us")
                mail.putExtra(Intent.EXTRA_TEXT,txtSendMessage.text.toString())
                try{
                    startActivity(Intent.createChooser(mail,"E-Stationary"))
                }catch (e:Exception){
                    Toast.makeText(this,e.toString(), Toast.LENGTH_SHORT).show()
                }
//                val talkToUs = HashMap<String, Any>()
//                talkToUs.put("Email",email.toString())
//                talkToUs.put("Chat",txtSendMessage.text.toString())
//                db.collection("$email").document("Messages")
//                    .set(talkToUs)
//                    .addOnSuccessListener { documentReference ->
//                        Toast.makeText(this,"Will get back to you soon", Toast.LENGTH_SHORT).show()
//                    }
//                    .addOnFailureListener(){
//                        e -> Toast.makeText(this,e.toString(), Toast.LENGTH_SHORT).show()
//                    }
            }
        }
    }
}