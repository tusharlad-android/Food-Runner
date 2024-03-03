package com.tushar.foodhub.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tushar.foodhub.R

class ForgotPassword_Activity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotpassword)
    }

    override fun onBackPressed() {
       val intent= Intent(this@ForgotPassword_Activity,Login_Activity::class.java)
        startActivity(intent)
        finish()
    }
}