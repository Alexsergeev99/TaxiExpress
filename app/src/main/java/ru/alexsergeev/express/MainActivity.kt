package ru.alexsergeev.express

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import ru.alexsergeev.express.navigation.Navigation
import ru.alexsergeev.express.screens.AfterOrderScreen
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
            window.statusBarColor = getColor(R.color.white)
            window.navigationBarColor = getColor(R.color.black)
            ExpressTheme {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp)),
                ) {
                    Surface(color = Color.Black, modifier = Modifier.fillMaxSize()) {
                        Navigation()
                    }
                }
            }
        }
    }
}