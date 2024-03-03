package com.tushar.foodhub.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.tushar.foodhub.Detailsdatabase.DBAsynctask
import com.tushar.foodhub.Detailsdatabase.DetailsEntity
import com.tushar.foodhub.R
import com.tushar.foodhub.util.RestaurantDetails

class DetailsRecyclerAdapter(val context: Context,val ItemDetails:ArrayList<RestaurantDetails>):RecyclerView.Adapter<DetailsRecyclerAdapter.DetailsViewHolder>() {
    lateinit var button:Button
    var value:Int=0;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.recycler_details_single_row,parent,false)
        return DetailsViewHolder(view)

    }

    override fun getItemCount(): Int {
        return ItemDetails.size
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        val RestaurantDetails = ItemDetails[position]
        holder.detailsName.text = RestaurantDetails.foodname
        holder.detailsPrice.text = RestaurantDetails.foodPrice


        val detailsEntity = DetailsEntity(
            RestaurantDetails.foodId.toInt(),
            RestaurantDetails.foodname,
            RestaurantDetails.foodPrice,
            RestaurantDetails.restaurantId.toInt()
        )
        holder.addButton.setOnClickListener {
            if (!DBAsynctask(context.applicationContext, detailsEntity, 1).execute().get()) {
                val result =
                    DBAsynctask(context.applicationContext, detailsEntity, 2).execute().get()
                if (result) {
                    Toast.makeText(context, "Added to Cart", Toast.LENGTH_SHORT).show()
                    holder.addButton.text="Remove"
                    value=value++

                }else{
                    Toast.makeText(context,"Some error has occured",Toast.LENGTH_SHORT).show()
                }
            }else{
                val result=DBAsynctask(context.applicationContext,detailsEntity,3).execute().get()
                if(result){
                Toast.makeText(context,"Removed from favourites",Toast.LENGTH_SHORT).show()
                    holder.addButton.text="Add"
                    value=value--

                }else{
                    Toast.makeText(context,"Some error has occured",Toast.LENGTH_SHORT).show()
                }
            }



        }


    }
    class DetailsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val detailsName: TextView = view.findViewById(R.id.singledetailsfullname)
        val detailsPrice: TextView = view.findViewById(R.id.singledetailsprice)
        val addButton: Button = view.findViewById(R.id.singledetailsbuttonadd)
        val serialNumber: TextView = view.findViewById(R.id.detailsserialnumber)


    }

}