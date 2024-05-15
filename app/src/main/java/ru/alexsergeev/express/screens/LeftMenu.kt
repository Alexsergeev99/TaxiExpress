package ru.alexsergeev.express.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.alexsergeev.express.R
import ru.alexsergeev.express.ui.theme.DarkGray
import ru.alexsergeev.express.ui.theme.DarkRed
import ru.alexsergeev.express.ui.theme.DarkYellow

@Composable
fun LeftMenu() {
    Column(modifier = Modifier
        .fillMaxWidth(0.5f)
        .fillMaxHeight()
        .background(DarkGray),
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        Row(modifier = Modifier
            .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(modifier = Modifier
                .padding(16.dp),
                text = "Александр",
                color = Color.White)
            Image(modifier = Modifier
                .padding(8.dp)
                .size(48.dp)
                .clip(CircleShape),
                painter = painterResource(id = R.drawable.avatar_no_photo),
                contentDescription = "avatar")
        }
        Button(modifier = Modifier
            .align(alignment = Alignment.CenterHorizontally),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(DarkRed),
            onClick = { /*TODO*/ }) {
            Icon(painter = painterResource(id = R.drawable.baseline_car_rental_24)
                , contentDescription = "carIcon")
            Text(text = "Мой аккаунт")
        }
        Button(modifier = Modifier
            .align(alignment = Alignment.CenterHorizontally),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(DarkRed),
            onClick = { /*TODO*/ }) {
            Icon(painter = painterResource(id = R.drawable.baseline_car_rental_24)
                , contentDescription = "carIcon")
            Text(text = "Мои поездки")
        }
        Button(modifier = Modifier
            .align(alignment = Alignment.CenterHorizontally),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(DarkRed),
            onClick = { /*TODO*/ }) {
            Icon(painter = painterResource(id = R.drawable.baseline_car_rental_24)
                , contentDescription = "carIcon")
            Text(text = "Настройки")
        }
    }
}