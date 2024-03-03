package com.tushar.foodhub.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.tushar.foodhub.Database.DBHelper
import com.tushar.foodhub.Database.helperclass
import com.tushar.foodhub.R


class Register_Activity:AppCompatActivity() {

    private  lateinit var sharedPreferences:SharedPreferences
    lateinit var etName:EditText
    lateinit var etEmail:EditText
    lateinit var etMobileno:EditText
    lateinit var etAddress:EditText
    lateinit var etPassword:EditText
    lateinit var etConfirmPassword:EditText
    lateinit var btnSignUp: Button
    lateinit var db: DBHelper

    lateinit var database:FirebaseDatabase
    lateinit var reference: DatabaseReference


    private lateinit var mobile: String
    private lateinit var pass: String
    private lateinit var rePass: String
    private lateinit var name: String
    private lateinit var email: String
    private lateinit var address: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        etName=findViewById(R.id.EtRegisterName)
        etEmail=findViewById(R.id.EtRegisterEmail)
        etMobileno=findViewById(R.id.EtRegisterNumber)
        etAddress=findViewById(R.id.EtRegisteraddress)
        etPassword=findViewById(R.id.EtRegisterPassword)
        etConfirmPassword=findViewById(R.id.EtRegisterConfirmPassword)
        btnSignUp=findViewById(R.id.btnRegister)
        sharedPreferences=getSharedPreferences(getString(R.string.shared_preferences),Context.MODE_PRIVATE)
       btnSignUp.setOnClickListener {
           database=FirebaseDatabase.getInstance()
           reference=database.getReference("users")

           name=etName.text.toString()
           mobile=etMobileno.text.toString()
           pass=etPassword.text.toString()
           email=etEmail.text.toString()
           address=etAddress.text.toString()
           val helper=helperclass(name,email,pass,mobile,address)
           reference.child(mobile).setValue(helper)
           Toast.makeText(this,"Signup Succesfull",Toast.LENGTH_SHORT).show()
           val intent=Intent(this,Login_Activity::class.java)
           startActivity(intent)
       }
    }


}

