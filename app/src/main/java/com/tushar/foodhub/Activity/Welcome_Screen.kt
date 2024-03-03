package com.tushar.foodhub.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.privacysandbox.tools.core.model.Types.boolean
import com.tushar.foodhub.R

class Welcome_Screen:AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    var SPLASH_DELAY:Long=1000
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.screen_welcome)
        super.onCreate(savedInstanceState)
        sharedPreferences =
            getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE)
        var hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn", false)
        if (hasLoggedIn) {
            val intent = Intent(this, MainActivity::class.java)
            Handler().postDelayed({
                val intent = Intent(this@Welcome_Screen, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, SPLASH_DELAY)
        } else{


            Handler().postDelayed({
                val intent = Intent(this@Welcome_Screen, Login_Activity::class.java)
                startActivity(intent)
                finish()
            }, SPLASH_DELAY)
    }

    }
}