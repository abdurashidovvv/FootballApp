package com.example.footballapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.footballapp.repository.MyFootballRepository

@Suppress("UNCHECKED_CAST")
class MyViewModelFactory(private val myFootballRepository: MyFootballRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyFootballViewModel::class.java)) {
            return MyFootballViewModel(myFootballRepository) as T
        }
        throw java.lang.IllegalArgumentException("Error")
    }
}