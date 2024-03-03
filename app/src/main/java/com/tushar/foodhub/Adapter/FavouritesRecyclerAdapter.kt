package com.tushar.foodhub.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tushar.foodhub.Activity.RestaurantDetails_Activity
import com.tushar.foodhub.Database.DBAsyncTask
import com.tushar.foodhub.Database.RestaurantEntity
import com.tushar.foodhub.R
import com.tushar.foodhub.util.Restaurant

class FavouritesRecyclerAdapter(val context: Context, val ItemList:ArrayList<Restaurant>):RecyclerView.Adapter<FavouritesRecyclerAdapter.FavouritesViewHolder>() {
    class FavouritesViewHolder(view: View):RecyclerView.ViewHolder(view){
        var resId:String="1"
        val resName: TextView =view.findViewById(R.id.txtfavResName)
        val resImage:ImageView=view.findViewById(R.id.imgfavResLogo)
        val resPrice:TextView=view.findViewById(R.id.txtfavResPrice)
        val resRating:TextView=view.findViewById(R.id.txtfavResRating)
        val resFavourites:ImageView=view.findViewById(R.id.imgfavResLogo)
        val restaurantdetails:CardView=view.findViewById(R.id.RestaurantDetails)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_favourites_single_row,parent,false)
        return FavouritesViewHolder(view)

    }

    override fun getItemCount(): Int {
       return ItemList.size
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        val restaurant=ItemList[position]
        holder.resName.text=restaurant.restaurantName
        holder.resPrice.text=restaurant.restaurantPrice
        holder.resRating.text=restaurant.restaurantRating
        Picasso.get().load(restaurant.restaurantImage)
            .error(R.mipmap.ic_launcher_round)
            .into(holder.resImage)
        holder.resId=restaurant.restaurantid

        val restaurantEntity=RestaurantEntity(

            restaurant.restaurantid.toInt(),
            restaurant.restaurantName,
            restaurant.restaurantRating,
            restaurant.restaurantPrice,
            restaurant.restaurantImage
        )
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
        holder.restaurantdetails.setOnClickListener {
            val intent = Intent(context, RestaurantDetails_Activity::class.java)
            intent.putExtra("id", restaurant.restaurantid)
            intent.putExtra("name", restaurant.restaurantName)
            context.startActivity(intent)
        }

    }
}