package com.tushar.foodhub.Fragments

import com.tushar.foodhub.Adapter.DashboardRecyclerAdapter
import com.tushar.foodhub.R
import com.tushar.foodhub.util.ConnectionManager
import com.tushar.foodhub.util.Restaurant


import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.*
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import org.json.JSONException
import kotlin.collections.HashMap

class DashboardFragment:Fragment() {
    lateinit var recyclerDashboard:RecyclerView

    val restaurantInfoList= arrayListOf<Restaurant>(
    )
    lateinit var layoutManager:RecyclerView.LayoutManager

    lateinit var recyclerAdapter: DashboardRecyclerAdapter




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.dashboard_fragment, container, false)
        setHasOptionsMenu(true)
        recyclerDashboard = view.findViewById(R.id.recyclerdashboard)
        layoutManager=LinearLayoutManager(activity)








        val queue=Volley.newRequestQueue(activity as Context)
        val url="http://13.235.250.119/v2/restaurants/fetch_result/"
        if(ConnectionManager().checkConnectivity(activity as Context)) {
            val jsonObjectRequest=object : JsonObjectRequest(Request.Method.GET,url,null, Response.Listener {

                try {
                    val dataobject = it.getJSONObject("data")
                    val success = dataobject.getBoolean("success")
                    if (success) {
                        val data = dataobject.getJSONArray("data")

                        for (i in 0 until data.length()) {
                            val bookJsonObject = data.getJSONObject(i)
                            val bookObject = Restaurant(
                                bookJsonObject.getString("id"),
                                bookJsonObject.getString("name"),


                                bookJsonObject.getString("rating"),
                                bookJsonObject.getString("cost_for_one"),
                                bookJsonObject.getString("image_url")

                            )
                            restaurantInfoList.add(bookObject)
                            recyclerAdapter =
                                DashboardRecyclerAdapter(activity as Context, restaurantInfoList)
                            recyclerDashboard.adapter = recyclerAdapter
                            recyclerDashboard.layoutManager = layoutManager
                        }


                    } else {
                        Toast.makeText(
                            activity as Context,
                            "Some Error has Occured",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch(e:JSONException) {
                    Toast.makeText(activity as Context,"Volley error has Occured",Toast.LENGTH_SHORT).show()
                }
            }




            ,Response.ErrorListener {
                Toast.makeText(activity as Context,"Volley error has Occured",Toast.LENGTH_SHORT).show()
            }){
                override fun getHeaders(): MutableMap<String, String> {
                    val headers=HashMap<String,String>()
                    headers["Content-type"]="application/json"
                    headers["token"]="9102e2e51be482"
                    return headers
                }
            }
            queue.add(jsonObjectRequest)
        }
        else{
            val dialog = AlertDialog.Builder(activity)
            dialog.setTitle("Error")
            dialog.setMessage("Internet Connection is not Found")
            dialog.setPositiveButton("Open Settings"){
                    text,listener ->
                val settingsIntent= Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingsIntent)
                activity?.finish()


            }
            dialog.setNegativeButton("Exit"){text,listener ->
                ActivityCompat.finishAffinity(activity as Activity)
            }
            dialog.create()
            dialog.show()

        }


        return view
    }










}
