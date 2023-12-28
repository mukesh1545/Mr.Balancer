package com.example.mrbalancer.ViewModel

import androidx.lifecycle.LiveData
import com.example.mrbalancer.RoomDataBase.BalancerDao
import com.example.mrbalancer.RoomDataBase.BalancerData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Balancer_repo(private  var BalancerDao:BalancerDao) {

    fun getAllNotes(): LiveData<List<BalancerData>> {
            return BalancerDao.getAllData()

    }

    fun insertNote(List: BalancerData) {
        CoroutineScope(Dispatchers.IO).launch {
            BalancerDao.Insert(List)
        }
    }
    fun incoming() :LiveData<Int>{
        return BalancerDao.Incoming()
    }
    fun outgoing() :LiveData<Int>{
        return BalancerDao.outgoing()
    }
    fun Balances() :LiveData<Int>{
        return BalancerDao.getBalance()
    }

}