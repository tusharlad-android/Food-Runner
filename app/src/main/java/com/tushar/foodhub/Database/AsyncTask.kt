package com.tushar.foodhub.Database



import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.room.Room


class DBAsyncTask(val context: Context, val restaurantEntity: RestaurantEntity, val mode: Int) :
    AsyncTask<Void, Void, Boolean>() {


    private val db =
        Room.databaseBuilder(context, RestaurantDatabase::class.java, "restaurant_db").build()

    override fun doInBackground(vararg p0: Void?): Boolean {

        when (mode) {
            1 -> { //checkRestaurantById
                val restaurant: RestaurantEntity? = db.restaurantDao()
                    .getRestaurantById(restaurantEntity.id)
                db.close()
                return restaurant != null
            }

            2 -> { //insertRestaurant
                db.restaurantDao().insertRestaurant(restaurantEntity)
                db.close()
                return true
            }

            3 -> { //deleteRestaurant
                db.restaurantDao().deleteRestaurant(restaurantEntity)
                db.close()
                return true
            }

            else -> return false

        }

    }
}



    class FavAsyncTask(context: Context) :
        AsyncTask<Void, Void, List<RestaurantEntity>>() {
        private val db =
            Room.databaseBuilder(context, RestaurantDatabase::class.java, "restaurant_db").build()

        override fun doInBackground(vararg params: Void?): List<RestaurantEntity> {

            return db.restaurantDao().getAllRestaurants()
        }
    }

