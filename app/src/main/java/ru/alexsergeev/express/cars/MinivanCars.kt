package ru.alexsergeev.express.cars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

val cars = listOf("Citroen C4 Grand Picasso", "Volkswagen Caddy")

@Composable
fun MinivanCars(navController: NavController, name: String?) {
    LazyColumn(
        modifier = Modifier
//            .fillMaxWidth(0.5f)
//            .fillMaxHeight(0.4f)
            .padding(bottom = 36.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        items(cars.size) { car ->
            Text(
                modifier = Modifier
                    .height(40.dp),
                text = cars[car],
                color = Color.Black
            )
        }
    }
}