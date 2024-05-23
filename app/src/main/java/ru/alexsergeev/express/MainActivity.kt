package ru.alexsergeev.express

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.alexsergeev.express.screens.CodeScreen
import ru.alexsergeev.express.screens.Contacts
import ru.alexsergeev.express.screens.FinalScreen
import ru.alexsergeev.express.screens.LeftMenu
import ru.alexsergeev.express.screens.MainPage
import ru.alexsergeev.express.screens.RateScreen
import ru.alexsergeev.express.screens.Registration
import ru.alexsergeev.express.screens.SplashScreen
import ru.alexsergeev.express.ui.theme.ExpressTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpressTheme {
                Column(
                    modifier = Modifier
                        .padding(top = 48.dp, bottom = 16.dp)
                        .clip(RoundedCornerShape(10.dp)),
                ) {
//                    Map()
//                    MainPage()
//                    RateScreen()
//                    Registration()
//                    LeftMenu()
                    Surface(color = Color.Black, modifier = Modifier.fillMaxSize()) {
                        Navigation()
//                        LeftMenu()
//                        RateScreen()
//                        EconomyRate()
                    }
                }
            }
        }
    }
}

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
        composable("main_screen/{name}/{phone}") {
            MainPage(
                navController = navController,
                it.arguments?.getString("name"),
                it.arguments?.getString("phone")
            )
        }
        composable("code_screen/{name}/{phone}") {
            CodeScreen(
                navController = navController,
                it.arguments?.getString("name"),
                it.arguments?.getString("phone")
            )
        }
        composable(route = "left_menu/{name}/{phone}") {
            Box {
                MainPage(
                    navController = navController,
                    it.arguments?.getString("name"),
                    it.arguments?.getString("phone")
                )
                LeftMenu(
                    navController = navController,
                    it.arguments?.getString("name"),
                    it.arguments?.getString("phone")
                )
            }
        }
        composable(route = "contacts/{name}/{phone}") {
            Contacts(
                navController = navController,
                it.arguments?.getString("name"),
                it.arguments?.getString("phone")
            )
        }
        composable(route = "rate_screen/{name}/{phone}/{from}/{to}/{date}/{time}/{passengers}") {
            RateScreen(
                navController = navController, it.arguments?.getString("name"),
                it.arguments?.getString("phone"),
                it.arguments?.getString("from"),
                it.arguments?.getString("to"),
                it.arguments?.getString("date"),
                it.arguments?.getString("time"),
                it.arguments?.getString("passengers")
            )
        }
        composable(route = "final_screen/{name}/{phone}/{from}/{to}/{date}/{time}/{passengers}/{rate}") {
            FinalScreen(
                navController = navController, it.arguments?.getString("name"),
                it.arguments?.getString("phone"),
                it.arguments?.getString("from"),
                it.arguments?.getString("to"),
                it.arguments?.getString("date"),
                it.arguments?.getString("time"),
                it.arguments?.getString("passengers"),
                it.arguments?.getString("rate")
            )
        }
//        composable(route = "minivans/{name}") {
//                Box {
//                    MinivanRate(navController = navController, it.arguments?.getString("name"))
//                    MinivanCars(navController = navController, it.arguments?.getString("name"))
//                }
//            }
    }
}
