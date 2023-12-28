package com.example.mrbalancer.RoomDataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BALANCESDATA")
data class BalancerData(

    val Amount:Int,
    val Type:String,
    val Tag:String,
    val Date:String,
    val Remark:String)
{@PrimaryKey(autoGenerate = true)
var id:Int = 0
}
