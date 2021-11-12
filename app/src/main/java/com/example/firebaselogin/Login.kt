package com.example.firebaselogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()


        btnlogin.setOnClickListener {

            var logemail=eteml.text.toString()
            var logpassword=etpass.text.toString()

            if(logemail.trim().isEmpty()){

                Toast.makeText(this, "Please enter your email", Toast.LENGTH_LONG).show()
            }
            else if (logpassword.trim().isEmpty()){
                Toast.makeText(this, "Please enter your password", Toast.LENGTH_LONG).show()
            }
            else
            auth.signInWithEmailAndPassword(logemail, logpassword).addOnCompleteListener {
                if(it.isSuccessful) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show()
                }
                else
                    Toast.makeText(this, it.exception?.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }

        btngotologinreg.setOnClickListener {
            var i=Intent(this, Registration::class.java)
            startActivity(i)
        }

    }
}