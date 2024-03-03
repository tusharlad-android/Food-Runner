package com.tushar.foodhub.Detailsdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tushar.foodhub.Database.RestaurantEntity

@Database(entities = [DetailsEntity::class],version=1)
abstract class DetailsDatabase:RoomDatabase(){
    abstract fun detailsDao():DetailsDao
}