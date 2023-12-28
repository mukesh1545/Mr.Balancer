package com.example.mrbalancer.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mrbalancer.RoomDataBase.BalancerData

class Balancer_ViewModel(private  var Repo:Balancer_repo) :ViewModel(){

     fun getAllData():LiveData<List<BalancerData>>
     {
         return  Repo.getAllNotes()
     }
    fun Insert(List:BalancerData){
        return Repo.insertNote(List)
    }
    fun Incoming():LiveData<Int>{
        return Repo.incoming()
    }
    fun outgoing():LiveData<Int>{
        return Repo.outgoing()
    }
    fun Balances():LiveData<Int>{
        return Repo.Balances()
    }

}