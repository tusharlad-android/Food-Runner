package com.tushar.foodhub.Detailsdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DetailsDao {
    @Insert
    fun insertdetails(details:DetailsEntity)
    @Delete
    fun deletedetails(details:DetailsEntity)
    @Query("SELECT * FROM details")
    fun getalldetails():List<DetailsEntity>
    @Query("SELECT * FROM details WHERE id= :foodId")
    fun getfoodbyid(foodId:Int):DetailsEntity

}