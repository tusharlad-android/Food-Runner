package com.tushar.foodhub.Fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.tushar.foodhub.Adapter.DashboardRecyclerAdapter

import com.tushar.foodhub.Adapter.FavouritesRecyclerAdapter
import com.tushar.foodhub.Database.FavAsyncTask
import com.tushar.foodhub.R
import com.tushar.foodhub.util.Restaurant

class FavouritesFragment : Fragment() {


    private lateinit var rvFavourites: RecyclerView
    private var restaurantList = arrayListOf<Restaurant>()
    private lateinit var progressBarLayout: RelativeLayout
    lateinit var layoutmanager:LayoutManager
    private lateinit var tvNoFav: TextView

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favourites, container, false)

        rvFavourites = view.findViewById(R.id.recyclerfavourites)
        progressBarLayout = view.findViewById(R.id.progressBarLayout)
        tvNoFav = view.findViewById(R.id.tvNoFav)

        progressBarLayout.visibility = View.VISIBLE

        val listFromAsync = FavAsyncTask(activity!!.applicationContext).execute().get()
        if (listFromAsync != null  ) {
            listFromAsync.forEach {
                val restaurant = Restaurant(it.id.toString(),
                    it.name,
                    it.rating.toString(),
                    it.price.toString(),
                    it.image)

                restaurantList.add(restaurant)
            }
            Log.i("array",restaurantList.toString())

            if (listFromAsync.isEmpty()) {
                tvNoFav.visibility = View.VISIBLE
                progressBarLayout.visibility =  View.GONE
            } else {
                layoutmanager=LinearLayoutManager(activity)
                rvFavourites.layoutManager =layoutmanager
                val recyclerAdapter=DashboardRecyclerAdapter(activity as Context,restaurantList)

                rvFavourites.adapter = recyclerAdapter
                progressBarLayout.visibility =  View.GONE
            }
        }


        return view
    }


}