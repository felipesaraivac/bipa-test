package com.saraiva.bipa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.saraiva.bipa.ui.navigation.RootNavigationGraph
import com.saraiva.bipa.ui.theme.BipaTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BipaTestTheme {
                RootNavigationGraph(navController = rememberNavController())
            }
        }
    }
}