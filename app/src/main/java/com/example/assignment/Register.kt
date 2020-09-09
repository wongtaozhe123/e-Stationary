package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.RegexValidator
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.register.*
import java.util.regex.Pattern

public class Register : AppCompatActivity() {

    val passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$")
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        auth=FirebaseAuth.getInstance()
        btnRegister.setOnClickListener{
            if(txtEmail.text.toString().isNotEmpty()&&txtPhoneNumber.text.toString().isNotEmpty()&&txtUsernameRegister.text.toString().isNotEmpty()&&
                    txtPasswordRegister.text.toString().isNotEmpty()&&txtConfirmPassword.text.toString().isNotEmpty()){
                if(txtPhoneNumber.text.trim().toString().length==10||txtPhoneNumber.text.trim().toString().length==11){
                    if(Patterns.EMAIL_ADDRESS.matcher(txtEmail.text.toString()).matches()){
                        if(txtPasswordRegister.text.trim().toString()==txtConfirmPassword.text.trim().toString()){
                            if(txtPasswordRegister.text.toString().length<6){
                                txtConfirmPassword.setError("Different confirmation password, please insert again")
                                Toast.makeText(this,"Password must have at least 6 characters", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                if(passwordPattern.matcher(txtPasswordRegister.text.toString()).matches()){
                                    if(chkboxAgreeTnC.isChecked){
                                        createUser(txtEmail.text.toString(),txtPasswordRegister.text.toString(),txtPhoneNumber.text.toString(),txtUsernameRegister.text.toString())
                                    }
                                    else{
                                        Toast.makeText(this,"Please read and agree with the terms and condition",Toast.LENGTH_SHORT).show()
                                    }
                                }
                                else{
                                    txtConfirmPassword.setError("Password must contain atleast one number, one upper and lowercase character, one special character and no whitespace")
                                    txtPasswordRegister.setError("Password must contain atleast one number, one upper and lowercase character, one special character and no whitespace")
                                    Toast.makeText(this,"Password is too weak", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                        else{
                            txtPasswordRegister.setError("Password confirmation different")
                            Toast.makeText(this,"Password confirmation different", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        txtEmail.setError("Invalid Email format")
                        Toast.makeText(this,"Invalid Email address format", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this,"Invalid phone number", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"Please fill in every column required", Toast.LENGTH_SHORT).show()
            }

        }
        btnLoginTemp.setOnClickListener(){
            startActivity(Intent(this,MainActivity::class.java))
        }
        btnRegistrationTemp.setOnClickListener(){
            startActivity(Intent(this,Register::class.java))
        }
    }
    fun createUser(email: String, password: String, phoneNo: String, userName: String){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){
            task ->
            if(task.isSuccessful){
                Toast.makeText(this,"Welcome to E-Stationary app", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,Home::class.java)
                startActivity(intent)

            }
            else{
                Toast.makeText(this,"Failure to create an account, please try again", Toast.LENGTH_SHORT).show()
                Log.e("Failure notification",task.exception.toString())
            }
        }
    }

//    override fun onStart() {
//        super.onStart()
//        val user=auth.currentUser;
//        if(user != null){
//            val intent = Intent(this,Home::class.java)
//            startActivity(intent)
//        }
//    }

}
