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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.alexsergeev.express.R
import ru.alexsergeev.express.ui.theme.DarkGray
import ru.alexsergeev.express.ui.theme.DarkRed
import ru.alexsergeev.express.ui.theme.DarkYellow

@Composable
fun LeftMenu(navController: NavController, userName : MutableState<String>) {
    Column(modifier = Modifier
        .fillMaxWidth(0.7f)
        .fillMaxHeight()
        .padding(bottom = 48.dp)
        .background(Color.Black),
        verticalArrangement = Arrangement.Top
    )
    {
        Row(modifier = Modifier
            .padding(top = 16.dp, start = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(modifier = Modifier
                .padding(16.dp),
                text = userName.value,
                color = Color.White,
                fontSize = 24.sp)
            Image(modifier = Modifier
                .padding(8.dp)
                .size(48.dp)
                .clip(CircleShape),
                painter = painterResource(id = R.drawable.baseline_account_circle_24),
                contentDescription = "avatar")
        }
        Button(modifier = Modifier
            .align(alignment = Alignment.Start),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(Color.Black, Color.White),
            onClick = {
                navController.navigate("main_screen")
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_car_rental_24),
                contentDescription = "carIcon"
            )
            Text(modifier = Modifier
                .padding(start = 8.dp),
                text = "Главная страница"
                , fontSize = 24.sp)
        }
        Button(modifier = Modifier
            .align(alignment = Alignment.Start),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(Color.Black, Color.White),
            onClick = { /*TODO*/ }) {
            Icon(painter = painterResource(id = R.drawable.baseline_account_circle_24)
                , contentDescription = "myAccount")
            Text(modifier = Modifier
                .padding(start = 8.dp),
                text = "Мой аккаунт"
                , fontSize = 24.sp)
        }
        Button(modifier = Modifier
            .align(alignment = Alignment.Start),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(Color.Black, Color.White),
            onClick = { /*TODO*/ }) {
            Icon(painter = painterResource(id = R.drawable.baseline_directions_car_24)
                , contentDescription = "carIcon")
            Text(modifier = Modifier
                .padding(start = 8.dp),
                text = "Мои поездки"
                , fontSize = 24.sp)
        }
        Button(modifier = Modifier
            .align(alignment = Alignment.Start),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(Color.Black, Color.White),
            onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_emoji_emotions_24),
                contentDescription = "settings"
            )
            Text(modifier = Modifier
                .padding(start = 8.dp),
                text = "Настройки"
                , fontSize = 24.sp)
        }
        Button(modifier = Modifier
            .align(alignment = Alignment.Start),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(Color.Black, Color.White),
            onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_contact_support_24),
                contentDescription = "help"
            )
            Text(modifier = Modifier
                .padding(start = 8.dp)
                , text = "Поддержка"
                , fontSize = 24.sp)
        }
//        LeftMenuNew()
        }
    }
