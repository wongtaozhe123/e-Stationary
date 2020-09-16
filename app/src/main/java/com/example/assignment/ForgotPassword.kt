package com.example.assignment

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.DocumentReference
import kotlinx.android.synthetic.main.forget_password.*
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

class ForgotPassword : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var db: DocumentReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forget_password)
        auth = FirebaseAuth.getInstance()
        db=FirebaseFirestore.getInstance().document("users/userDetail")
        verifyCredentials.setOnClickListener(){
            if(txtPhoneNumber.text.toString().isNotEmpty()&&txtEmail.text.toString().isNotEmpty()){
                checkCredentials()
            }
            else{
                Toast.makeText(this,"Please fill in your email and phone number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun checkCredentials(){
        var tempEmail = txtEmail.text.toString()
        db.collection("userParticular").document("$tempEmail")
            .get().addOnSuccessListener { result ->
                if(result!=null){

                    var phone = result.getString("phoneNumber")
                    if(txtPhoneNumber.text.toString()==phone){
//                        val intent = Intent(this,ResetPassword::class.java)
//                        intent.putExtra("email",txtEmail.text.toString())
//                        startActivity(intent)
                        var temps = FirebaseAuth.getInstance()
                        temps.sendPasswordResetEmail(txtEmail.text.toString()).addOnCompleteListener(){
                                task -> if(task.isSuccessful){
                                            Toast.makeText(this,"Please go to email and reset your password", Toast.LENGTH_SHORT).show()
                                        }
                                        else{
                                            Toast.makeText(this,"Error in resetting, please restart the app again", Toast.LENGTH_SHORT).show()
                                        }
                        }
                            .addOnFailureListener(){
                                    e -> Toast.makeText(this,e.toString(), Toast.LENGTH_SHORT).show()
                            }
                    }

                    else{
                        Toast.makeText(this,"Invalid phone number or email", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this,"No data to show", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener{
                e -> Toast.makeText(this,e.toString(), Toast.LENGTH_SHORT).show()
            }
        }
}