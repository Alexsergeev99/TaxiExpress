package ru.alexsergeev.express

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.alexsergeev.express.screens.LeftMenu
import ru.alexsergeev.express.screens.MainPage
import ru.alexsergeev.express.screens.Map
import ru.alexsergeev.express.screens.Registration
import ru.alexsergeev.express.ui.theme.ExpressTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpressTheme {
                Column(modifier = Modifier
                    .padding(8.dp)
//                    .background(DarkGrey)
                ) {
//                    Map()
//                    MainPage()
//                    RateScreen()
//                    Registration()
                    LeftMenu()
                }
            }
        }
    }
}
