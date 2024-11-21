package com.example.coffeeroute

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.coffeeroute.ui.theme.CoffeeRouteTheme
import com.google.firebase.FirebaseApp


class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        //enableEdgeToEdge()
        setContent {
            CoffeeRouteTheme {

                Surface(
                    color = MaterialTheme.colorScheme.background,
                    shape = MaterialTheme.shapes.medium
                ) {

                    MyApp()
                }
            }
        }
    }

}