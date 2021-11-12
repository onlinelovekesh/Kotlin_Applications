package com.example.firebaselogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registration.*

class Registration : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        auth = FirebaseAuth.getInstance()

            //login button
        btngotologin.setOnClickListener {
            var i = Intent(this, Login::class.java)
            startActivity(i)
        }

            //save button
        btnsave.setOnClickListener {
            var email: String = eml.text.toString()
            var password: String = pwd.text.toString()
            var confirmpassword: String = cnfpwd.text.toString()

            if (email.trim().isEmpty()) {       // if (check(email))
                Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_LONG).show()
            } else if (password.trim().isEmpty()) {
                Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_LONG).show()
            } else if (confirmpassword.trim().isEmpty()) {
                Toast.makeText(this, "Confirm password cannot be empty", Toast.LENGTH_LONG).show()
            } else if (password != confirmpassword) {
                Toast.makeText(this,"Password and Confirm Password are different",Toast.LENGTH_LONG).show()
            } else {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener() {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Hurray! You are Registered", Toast.LENGTH_LONG).show()
                        Handler(Looper.getMainLooper()).postDelayed({
                            // using explicit intent which is used to switch from one activity to other
                            val i=Intent(this, Login::class.java) //crating object i  and :: is scope resolution operator
                            startActivity(i)

                            //finish();
                        },2000);

                    }
                    else
                        Toast.makeText(this, it.exception?.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    //fun check(str: String):Boolean {
    //    if(str==""){
    //        Toast.makeText(this, "Field is blank", Toast.LENGTH_LONG).show()
    //        return true
    //    }
    //    else
    //        return false
    //}
}
