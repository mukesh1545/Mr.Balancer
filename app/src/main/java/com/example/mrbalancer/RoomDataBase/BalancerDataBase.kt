package com.example.mrbalancer.RoomDataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BalancerData::class], version = 2, exportSchema = false)
abstract class BalancerDataBase :RoomDatabase() {

    companion object{
        private var INSTANCE:BalancerDataBase?=null

        fun getInstances(context: Context):BalancerDataBase?
        {
            if(INSTANCE==null)
            {
                synchronized(BalancerDataBase::class.java)
                {
                    INSTANCE= Room.databaseBuilder(
                        context,BalancerDataBase::class.java,"BalancerDataBase"
                    ).build()

                }
                return INSTANCE
            }
            return INSTANCE
        }
    }

    abstract  fun BalancerDao():BalancerDao
}