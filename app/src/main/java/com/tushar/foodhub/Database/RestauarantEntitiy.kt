package com.tushar.foodhub.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class RestaurantEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val rating: String,
    val price: String,
    val image: String
)