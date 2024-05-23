package ru.alexsergeev.express.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.alexsergeev.express.R
import ru.alexsergeev.express.ui.theme.DarkYellow

@Composable
fun Contacts(navController: NavController, name: String?, phone: String?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    )
    {
        IconButton(modifier = Modifier
            .padding(top = 32.dp, start = 32.dp)
            .size(32.dp),
            onClick = {
                navController.navigate("left_menu/${name.toString()}/${phone.toString()}")
            }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_density_medium_24),
                contentDescription = "menu",
                tint = Color.White
            )
        }
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .size(120.dp)
                .padding(top = 48.dp)
                .align(alignment = Alignment.TopCenter),
            painter = painterResource(id = R.drawable.slavexpresslogo__horisontal_),
            contentDescription = "test image"
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                color = DarkYellow,
                text = "Контакты",
                fontSize = 24.sp
            )
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "phoneNumbers",
                    tint = Color.White
                )
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .align(alignment = Alignment.CenterVertically),
                    color = Color.White,
                    text = "+7(999)407-05-31, +7(930)084-07-48"
                )
            }
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = "eMail",
                    tint = Color.White
                )
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .align(alignment = Alignment.CenterVertically),
                    color = Color.White,
                    text = "https://vk.com/slavicexp"
                )
            }
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "eMail",
                    tint = Color.White
                )
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .align(alignment = Alignment.CenterVertically),
                    color = Color.White,
                    text = "https://t.me/SlavyanskyExpress"
                )
            }
        }
    }
}