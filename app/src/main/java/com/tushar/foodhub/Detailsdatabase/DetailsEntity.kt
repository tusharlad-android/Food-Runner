package com.tushar.foodhub.Detailsdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "details")
data class DetailsEntity (
    @PrimaryKey val id:Int,
    val foodname:String,
    val foodprice:String,
    val restaurantId:Int

        )
