package ru.alexsergeev.express.rates

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.alexsergeev.express.R
import ru.alexsergeev.express.ui.theme.DarkYellow

@Composable
fun MinivanRate(navController: NavController, name: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 32.dp, top = 8.dp, start = 8.dp, end = 8.dp)
            .background(Color.Black)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(bottom = 32.dp, top = 8.dp, start = 8.dp, end = 8.dp)
                .size(200.dp)
                .clip(CircleShape)
                .align(alignment = Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.mers_minivan),
            contentDescription = "minivan"
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 32.dp, top = 8.dp, start = 8.dp, end = 8.dp) ,
            text = "Mercedes V-Class, Kia Carnival, Volkswagen Transporter",
            color = Color.White,
            fontSize = 20.sp
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 32.dp, top = 8.dp, start = 8.dp, end = 8.dp),
            text = "От 15 рублей за километр",
            color = Color.White,
            fontSize = 28.sp
        )
        Button(modifier = Modifier
            .padding(bottom = 32.dp, top = 8.dp, start = 8.dp, end = 8.dp)
            .align(Alignment.CenterHorizontally)
            .fillMaxWidth(0.5f),
            colors = ButtonDefaults.buttonColors(DarkYellow),
            onClick = {
                navController.navigate("final_screen/${name.toString()}")
            }
        ) {
            Text(
                text = "Выбрать тариф",
                color = Color.Black
            )
        }
    }
}