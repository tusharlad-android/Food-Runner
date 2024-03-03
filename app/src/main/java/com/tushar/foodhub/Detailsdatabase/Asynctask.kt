package com.tushar.foodhub.Detailsdatabase

import android.content.Context
import android.os.AsyncTask
import androidx.room.Room


class DBAsynctask(val context: Context, val detailsEntity: DetailsEntity, val mode:Int):
        AsyncTask<Void,Void,Boolean>(){
        private val db= Room.databaseBuilder(context,DetailsDatabase::class.java,"details_db").build()
        override fun doInBackground(vararg params: Void?): Boolean {
            when(mode){
                1 ->{
                    //checkRestaurant by id
                    val details:DetailsEntity?=db.detailsDao().getfoodbyid(detailsEntity.id)
                    db.close()
                    return details!=null
                }
                2->{
                    //insertRestaurant
                    db.detailsDao().insertdetails(detailsEntity)
                    db.close()
                    return true
                }
                3->{
                    db.detailsDao().deletedetails(detailsEntity)
                    db.close()
                    return true
                }
                4->{
                    val details:List<DetailsEntity>?=db.detailsDao().getalldetails()
                    db.close()
                    return details!=null


                }

                else->return false




            }
        }

    }
class CartasyncTask(context: Context):AsyncTask<Void,Void,List<DetailsEntity>>(){
    private val db=
        Room.databaseBuilder(context,DetailsDatabase::class.java,"restaurant_db").build()
    override fun doInBackground(vararg params: Void?): List<DetailsEntity> {
        return db.detailsDao().getalldetails()

    }

}


