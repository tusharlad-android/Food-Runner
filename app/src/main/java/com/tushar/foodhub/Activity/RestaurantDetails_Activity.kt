package com.tushar.foodhub.Activity

import android.os.Bundle
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.tushar.foodhub.Detailsdatabase.DBAsynctask
import com.tushar.foodhub.Fragments.DetailsFragment
import com.tushar.foodhub.R


class RestaurantDetails_Activity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    var restaurantname:String?="name"
    lateinit var btnaddtofav: Button
    var id:String?="0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_default)
        toolbar=findViewById(R.id.detailstoolbar)
        btnaddtofav=findViewById(R.id.gotofav)






        if(intent!=null) {
            restaurantname = intent.getStringExtra("name")

            id = intent.getStringExtra("id")
        }
        val bundle=Bundle()
        bundle.putString("id",id)


        val fragment=DetailsFragment()
        fragment.arguments=bundle
        supportFragmentManager.beginTransaction().replace(
            R.id.detailsframe,fragment
        ).commit()
        setUpToolbar()
    }
    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title=restaurantname
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

}


