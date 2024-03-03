package com.tushar.foodhub.Activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.tushar.foodhub.R
import java.util.Objects


class Login_Activity :AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    var  PREFS_NAME="MyPrefsFile"

    lateinit var EtMobileNumber:EditText
    lateinit var EtPassword:EditText
    lateinit var btnLogin:Button
    lateinit var txtForgot:TextView
    lateinit var txtRegister: TextView



    private lateinit var mobile:String
    private lateinit var password:String


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        EtMobileNumber = findViewById(R.id.EtloginMobileNumber)
        EtPassword = findViewById(R.id.EtloginPassword)
        btnLogin = findViewById(R.id.btnlogin)
        txtForgot = findViewById(R.id.txtloginForgot)
        txtRegister = findViewById(R.id.txtloginRegister)
        sharedPreferences =
            getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE)

        btnLogin.setOnClickListener {
            if(!validateUsername()&&!validatePassword()){

        }
            else{
                CheckUser()
                
            }
        }




        txtForgot.setOnClickListener {
            val intent = Intent(this@Login_Activity, ForgotPassword_Activity::class.java)
            startActivity(intent)

            finish()
        }
        txtRegister.setOnClickListener {
            val intent = Intent(this@Login_Activity, Register_Activity::class.java)
            startActivity(intent)

            finish()
        }

    }
    fun validateUsername():Boolean{
        mobile=EtMobileNumber.text.toString()
        if(mobile.isEmpty()){
            EtMobileNumber.setError("Username cannot be empty")
            return false
        }else{
            EtMobileNumber.setError(null)
            return true
        }

    }
    fun validatePassword():Boolean{
       password=EtPassword.text.toString()
        if(mobile.isEmpty()){
            EtPassword.setError("Password cannot be empty")
            return false
        }else{
            EtPassword.setError(null)
            return true
        }

    }
    fun CheckUser(){
        mobile=EtMobileNumber.text.toString()
        password=EtPassword.text.toString()

        val reference:DatabaseReference=FirebaseDatabase.getInstance().getReference("users")
        val checkUserDatabase: com.google.firebase.database.Query =reference.orderByChild("mobileNo").equalTo(mobile)
        checkUserDatabase.addListenerForSingleValueEvent(object:ValueEventListener{
            @SuppressLint("SuspiciousIndentation")
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    EtMobileNumber.setError(null)
                    val passwordFromDb:String?=snapshot.child(mobile).child("password").getValue(String::class.java )
                        if(Objects.equals(passwordFromDb,password)){
                            EtMobileNumber.setError(null)
                            sharedPreferences.edit().putBoolean("hasLoggedIn",true).commit()
                            sharedPreferences.edit().putString("mobile",mobile).commit()
                            val intent=Intent(this@Login_Activity,MainActivity::class.java)
                            intent.putExtra("mobile",mobile)
                            startActivity(intent)




                        }else{
                            EtPassword.setError("Invalid Credentials")
                            EtPassword.requestFocus()
                        }

                }else{
                    EtMobileNumber.setError("User Doesnot exists")
                    EtMobileNumber.requestFocus()
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

}