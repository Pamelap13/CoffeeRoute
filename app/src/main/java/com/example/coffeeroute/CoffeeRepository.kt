package com.example.coffeeroute

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CoffeeRepository {
    private val db = FirebaseFirestore.getInstance()
    private val _itemsCoffee = MutableStateFlow<List<ItemCoffee>>(emptyList())
    val itemsCoffee: StateFlow<List<ItemCoffee>> = _itemsCoffee

    fun getCoffees(){
        db.collection("cafeterias").get().addOnSuccessListener { result ->
            val fetchCafeterias = result.map { document ->
                ItemCoffee(
                    id = document.getLong("id")?.toInt() ?: 0,
                    name = document.getString("nombre") ?: "",
                    address = document.getString("direccion") ?: "",
                    instagram = document.getString("instagram") ?: "",
                    image = document.getString("imagen") ?: "",
                    coordinate = document.getGeoPoint("coordenadas")
                        ?: GeoPoint(0.0, 0.0)
                )
            }
            _itemsCoffee.value = fetchCafeterias
        }
    }

}