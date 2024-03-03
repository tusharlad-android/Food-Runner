package com.tushar.foodhub.Activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.annotation.RequiresApi

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.tushar.foodhub.Database.DBHelper
import com.tushar.foodhub.Fragments.DashboardFragment
import com.tushar.foodhub.Fragments.FAQFragment
import com.tushar.foodhub.Fragments.FavouritesFragment
import com.tushar.foodhub.Fragments.OrderHistoryFragment
import com.tushar.foodhub.Fragments.ProfileFragment
import com.tushar.foodhub.R

class MainActivity:AppCompatActivity() {
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView



    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.default_dashboard)
        super.onCreate(savedInstanceState)
        drawerLayout=findViewById(R.id.drawerLayout)
        coordinatorLayout=findViewById(R.id.coordinatorLayout)
        toolbar=findViewById(R.id.toolbar)
        frameLayout=findViewById(R.id.frame)
        navigationView=findViewById(R.id.navigationView)
        setUpToolbar()
        openDashboard()
        actionBarDrawerToggle= ActionBarDrawerToggle(this@MainActivity,drawerLayout,
        R.string.open_drawer,R.string.close_drawer
            )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
       actionBarDrawerToggle.syncState()
       val username=intent.getStringExtra("username")
       var mobile=intent.getStringExtra("mobile")
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.itmDashboard ->{
                    openDashboard()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    navigationView.setCheckedItem(R.id.itmDashboard)

                }
                R.id.itmFavourites->{
                    supportFragmentManager.beginTransaction().replace(R.id.frame,FavouritesFragment()).commit()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    supportActionBar?.title="Favourites"
                    navigationView.setCheckedItem(R.id.itmFavourites)
                }
                R.id.itmFAQ->{
                    supportFragmentManager.beginTransaction().replace(R.id.frame,FAQFragment()).commit()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    supportActionBar?.title="Favourites"
                    navigationView.setCheckedItem(R.id.itmFAQ)
                }
                R.id.itmorderhistory->{
                    supportFragmentManager.beginTransaction().replace(R.id.frame,OrderHistoryFragment()).commit()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    supportActionBar?.title="Order History"
                    navigationView.setCheckedItem(R.id.itmorderhistory)
                }
                R.id.itmProfile->{
                    supportFragmentManager.beginTransaction().replace(R.id.frame,ProfileFragment()).commit()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    supportActionBar?.title="Profile"
                    navigationView.setCheckedItem(R.id.itmProfile)
                }
                R.id.itmlogout->{
                    val intent=Intent(this@MainActivity,Login_Activity::class.java)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    startActivity(intent)
                }

            }
            return@setNavigationItemSelectedListener true
        }


    }
    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="Dashboard"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        if(id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
    fun openDashboard(){
        supportFragmentManager.beginTransaction().replace(R.id.frame,DashboardFragment()).commit()
        supportActionBar?.title="Dashboard"
    }



}