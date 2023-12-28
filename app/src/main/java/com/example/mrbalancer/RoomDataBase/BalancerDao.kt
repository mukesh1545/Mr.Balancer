package com.example.mrbalancer.RoomDataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query



@Dao
interface BalancerDao {

    @Query("SELECT * FROM  BALANCESDATA order by id Desc")
    fun getAllData():LiveData<List<BalancerData>>
    @Insert
    fun Insert(List:BalancerData)
    @Query("SELECT SUM(AMOUNT) FROM BALANCESDATA WHERE Type LIKE 'CREDITED'")
    fun Incoming():LiveData<Int>

    @Query("SELECT SUM(AMOUNT) FROM BALANCESDATA WHERE Type LIKE 'DEBITED'")
    fun outgoing():LiveData<Int>

//    @Query("SELECT SUM(CASE WHEN Type = 'DEBITED' THEN - AMOUNT ELSE + AMOUNT END) FROM BALANCESDATA")
//    fun getBalances(): LiveData<Int>


    @Query("SELECT COALESCE(SUM(CASE WHEN TRIM(LOWER(Type)) = 'credited' THEN Amount ELSE -Amount END), 0) AS balance FROM BALANCESDATA")
    fun getBalance(): LiveData<Int>


    @Query("DELETE FROM BALANCESDATA WHERE id = :itemId")
    fun deleteItemById(itemId: Int)


}