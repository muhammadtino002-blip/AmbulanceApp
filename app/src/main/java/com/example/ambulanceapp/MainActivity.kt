package com.example.ambulanceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ambulanceapp.navigation.AmbulanceNavGraph
import com.example.ambulanceapp.ui.theme.AmbulanceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmbulanceAppTheme {
                AmbulanceNavGraph()
            }
        }
    }
}
