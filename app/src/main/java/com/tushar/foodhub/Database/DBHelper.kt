
    package com.tushar.foodhub.Database

    import android.annotation.SuppressLint
    import android.content.ContentValues
    import android.content.Context
    import android.database.sqlite.SQLiteDatabase
    import android.database.sqlite.SQLiteOpenHelper
    import android.os.Build
    import android.service.autofill.UserData
    import androidx.annotation.RequiresApi
    import androidx.compose.runtime.currentRecomposeScope

    import com.tushar.foodhub.util.info


    class DBHelper(context: Context): SQLiteOpenHelper(context,"Userdata",null,1) {
        override fun onCreate(p0: SQLiteDatabase?) {
            p0?.execSQL("create table Userdata (resId TEXT primary key,resName TEXT,resPrice TEXT,resRating TEXT)")
        }

        override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            p0?.execSQL("drop table if exists Userdata")
        }
        fun insertdata(resID:String,resName:String,resPrice:String,resRating:String):Boolean{
            val p0=this.writableDatabase
            val cv= ContentValues()
            cv.put("Id",resID)
            cv.put("Name",resName)
            cv.put("Price",resPrice)
            cv.put("Rating",resRating)

            val result=p0.insert("Userdata",null,cv)
            if(result==-1.toLong()){
                return false
            }
            return true

        }
        fun deletedata(resID:String):Boolean{
            val p0=this.writableDatabase
            val result=p0.delete("Userdata",null, arrayOf(resID))
            if(result==-1){
                return false
            }
            else{
                return true
            }
        }
        fun checkResID(resID:String):Boolean {
            val p0=this.writableDatabase
            val query="select * from Userdata where Id= :resID"
            val cursor=p0.rawQuery(query,null)
            if(cursor.count<=0){
                cursor.close()
                return false
            }
            else{
                cursor.close()
                return true
            }
            p0.close()
        }



    }
