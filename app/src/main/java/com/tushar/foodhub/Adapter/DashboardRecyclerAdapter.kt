package com.tushar.foodhub.Adapter

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.tushar.foodhub.R
import com.tushar.foodhub.util.Restaurant
import com.squareup.picasso.Picasso
import com.tushar.foodhub.Activity.RestaurantDetails_Activity
import com.tushar.foodhub.Fragments.DetailsFragment
import kotlinx.coroutines.NonDisposableHandle.parent
import androidx.fragment.app.Fragment
import com.tushar.foodhub.Database.DBAsyncTask
import com.tushar.foodhub.Database.DBHelper
import com.tushar.foodhub.Database.RestaurantEntity
import com.tushar.foodhub.util.Restaurants

class DashboardRecyclerAdapter(val context: Context, val Itemlist:ArrayList<Restaurant>) :RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_dashboard_single_row, parent, false)
        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Itemlist.size
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {


        val restaurant = Itemlist[position]
        val restaurantEntity = RestaurantEntity(
            restaurant.restaurantid.toInt(),
            restaurant.restaurantName,
            restaurant.restaurantRating,
            restaurant.restaurantPrice,
            restaurant.restaurantImage
        )
        holder.resName.text=restaurant.restaurantName
        holder.resPrice.text=restaurant.restaurantPrice
        holder.resRating.text=restaurant.restaurantRating
        holder.resID=restaurant.restaurantid
        Picasso.get().load(restaurant.restaurantImage)
            .error(R.mipmap.ic_launcher_round)
            .into(holder.resImage)
        holder.resFavourites.setOnClickListener {
            if (!DBAsyncTask(context.applicationContext, restaurantEntity, 1).execute().get()) {
                val result =
                    DBAsyncTask(context.applicationContext, restaurantEntity, 2).execute().get()
                if (result) {
                    Toast.makeText(context, "Added to favourites", Toast.LENGTH_SHORT).show()
                    holder.resFavourites.setBackgroundResource(R.drawable.ic_favourites)
                } else {
                    Toast.makeText(context, "Some error occurred", Toast.LENGTH_SHORT).show()
                }

            } else {
                val result =
                    DBAsyncTask(context.applicationContext, restaurantEntity, 3).execute().get()
                if (result) {
                    Toast.makeText(context, "Removed from favourites", Toast.LENGTH_SHORT).show()
                    holder.resFavourites.setBackgroundResource(R.drawable.baseline_favorite_border_24)
                } else {
                    Toast.makeText(context, "Some error occurred", Toast.LENGTH_SHORT).show()
                }

            }


        }
        holder.restaurantDetails.setOnClickListener {
            val intent = Intent(context, RestaurantDetails_Activity::class.java)
            intent.putExtra("id", restaurant.restaurantid)
            intent.putExtra("name", restaurant.restaurantName)
            context.startActivity(intent)
        }
    }
    class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val resImage: ImageView = view.findViewById(R.id.imgsingleRestaruantLogo)
        val resName: TextView = view.findViewById(R.id.txtsingleRestaurantname)
        val resPrice: TextView = view.findViewById(R.id.txtsinglePrice)
        val resRating: TextView = view.findViewById(R.id.txtsingleRating)
        val resFavourites: ImageView = view.findViewById(R.id.ivFavourite)
        val restaurantDetails: CardView = view.findViewById(R.id.RestaurantDetails)
        var resID: String = "1"
    }



}

