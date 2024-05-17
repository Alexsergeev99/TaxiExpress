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
import ru.alexsergeev.express.screens.LeftMenu
import ru.alexsergeev.express.screens.MainPage
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
        composable(
            "main_screen/{name}"
        ) {
            MainPage(navController = navController, it.arguments?.getString("name"))
        }
        composable(route = "left_menu/{name}") {
            Box {
                MainPage(navController = navController, it.arguments?.getString("name"))
                LeftMenu(navController = navController, it.arguments?.getString("name"))
            }
        }
    }
}
