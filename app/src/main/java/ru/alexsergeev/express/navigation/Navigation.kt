package ru.alexsergeev.express.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.alexsergeev.express.screens.AfterOrderScreen
import ru.alexsergeev.express.screens.CodeScreen
import ru.alexsergeev.express.screens.Contacts
import ru.alexsergeev.express.screens.FinalScreen
import ru.alexsergeev.express.screens.LeftMenu
import ru.alexsergeev.express.screens.MainPage
import ru.alexsergeev.express.screens.RateScreen
import ru.alexsergeev.express.screens.Registration
import ru.alexsergeev.express.screens.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        composable("registration") {
            Registration(navController = navController)
        }
        composable("main_screen") {
            MainPage(navController = navController)
        }
        composable("code_screen/{verificationID}") {
            CodeScreen(
                navController = navController,
                verificationID = it.arguments?.getString("verificationID")
            )
        }
        composable(route = "left_menu") {
            Box {
                MainPage(navController = navController)
                LeftMenu(navController = navController)
            }
        }
        composable(route = "contacts") {
            Contacts(navController = navController)
        }
        composable(route = "rate_screen") {
            RateScreen(navController = navController)
        }
        composable(route = "final_screen") {
            FinalScreen(navController = navController)
        }
        composable(route = "after_screen") {
            AfterOrderScreen(navController = navController)
        }
    }
}
