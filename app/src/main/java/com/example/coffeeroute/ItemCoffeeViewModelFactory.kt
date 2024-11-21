package com.example.coffeeroute

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ItemCoffeeViewModelFactory(private val coffeeRepository: CoffeeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemCoffeeViewModel::class.java)){
            return ItemCoffeeViewModel(coffeeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}