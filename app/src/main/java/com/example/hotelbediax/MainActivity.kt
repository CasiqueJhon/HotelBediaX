package com.example.hotelbediax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hotelbediax.presentation.ui.AddNewDestinationScreen
import com.example.hotelbediax.presentation.ui.DestinationDetailScreen
import com.example.hotelbediax.presentation.ui.DestinationListScreen
import com.example.hotelbediax.presentation.ui.SplashScreen
import com.example.hotelbediax.theme.HotelBediaXTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HotelBediaXTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "splash"
                ) {
                    composable("splash") {
                        SplashScreen(navController)
                    }

                    composable("destinationList") {
                        DestinationListScreen(
                            onAddDestinationClick = {
                                navController.navigate("addDestination")
                            },
                            onDestinationClick = { destinationId ->
                                navController.navigate("destinationDetail/$destinationId")
                            }
                        )
                    }

                    composable("addDestination") {
                        AddNewDestinationScreen(
                            onDestinationAdded = {
                                navController.popBackStack()
                            },
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable(
                        route = "destinationDetail/{destinationId}",
                        arguments = listOf(navArgument("destinationId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val destinationId = backStackEntry.arguments?.getInt("destinationId") ?: 0
                        DestinationDetailScreen(destinationId = destinationId)
                    }
                }
            }
        }
    }
}