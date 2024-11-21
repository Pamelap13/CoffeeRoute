package com.example.coffeeroute

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.json.Json

class ItemCoffeeViewModel(val coffeeRepository: CoffeeRepository) : ViewModel() {

    val itemsCoffee: StateFlow<List<ItemCoffee>> = coffeeRepository.itemsCoffee

    fun fecthItemsCoffee() {
        coffeeRepository.getCoffees()
    }

    fun decodeJson(json: String?): ItemCoffee?{
        return json?.let { Json.decodeFromString<ItemCoffee>(it) }
    }

}