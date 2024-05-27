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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.alexsergeev.express.R

@Composable
fun AfterOrderScreen(navController: NavController, name: String?) {

    val ctx = LocalContext.current
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black), contentAlignment = Alignment.Center
    )
    {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .size(240.dp)
                .padding(top = 48.dp)
                .align(alignment = Alignment.TopCenter),
            painter = painterResource(id = R.drawable.slavexpresslogo__horisontal_),
            contentDescription = "test image"
        )
        Column {
            Text(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                color = Color.White,
                fontSize = 24.sp,
                text = "$name, спасибо за Ваш заказ!"
            )
            Text(
                modifier = Modifier
                    .padding(bottom = 20.dp, start = 40.dp, end = 40.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                color = Color.White,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                text = "Наши менеджеры свяжутся с Вами в ближайшее время."
            )
            Text(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                color = Color.White,
                text = "Если у Вас остались вопросы или пожелания:"
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
