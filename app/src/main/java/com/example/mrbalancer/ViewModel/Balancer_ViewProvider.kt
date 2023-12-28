package com.example.mrbalancer.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class Balancer_ViewProvider(private val repository: Balancer_repo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Balancer_ViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return Balancer_ViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}