package com.tushar.foodhub.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.tushar.foodhub.R

class ProfileFragment: Fragment() {
    private lateinit var database:FirebaseDatabase
    lateinit var sharedPreferences: SharedPreferences
    lateinit var profilename: TextView
    lateinit var profilemobileNumber:TextView
    lateinit var profileemail:TextView
    lateinit var profileaddress:TextView
    lateinit var mobile:String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_profile,container,false)
        profilename=view.findViewById(R.id.profilename)
        profileemail=view.findViewById(R.id.profileemail)
        profilemobileNumber=view.findViewById(R.id.profilenumber)
        profileaddress=view.findViewById(R.id.profileAddress)
        sharedPreferences=requireActivity().getSharedPreferences(getString(R.string.shared_preferences),Context.MODE_PRIVATE)
        val mobile: String? =sharedPreferences.getString("mobile","")

        database=FirebaseDatabase.getInstance()
        val reference=database.getReference("users")
        val checkUserData:com.google.firebase.database.Query=reference.orderByChild("mobileNo").equalTo(mobile)
        checkUserData.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    profileemail.text=snapshot.child(mobile.toString()).child("email").getValue(String::class.java)
                    profileaddress.text=snapshot.child(mobile.toString()).child("address").getValue(String::class.java)
                    profilename.text=snapshot.child(mobile.toString()).child("name").getValue(String::class.java)
                    profilemobileNumber.text=mobile

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })





        return view
    }
}