package ru.alexsergeev.express.rates

import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.alexsergeev.express.LockScreenOrientation
import ru.alexsergeev.express.R
import ru.alexsergeev.express.cars.MinivanCars
import ru.alexsergeev.express.ui.theme.DarkRed
import ru.alexsergeev.express.ui.theme.DarkYellow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MinivanRate(
    navController: NavController,
    name: String?,
    phone: String?,
    from: String?,
    to: String?,
    date: String?,
    time: String?,
    passengers: String?
) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    val dialogState = rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp, top = 8.dp, start = 8.dp, end = 8.dp)
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(bottom = 8.dp, top = 8.dp, start = 8.dp, end = 8.dp)
                .size(200.dp)
//                .clip(CircleShape)
                .align(alignment = Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.mers_minivan),
            contentDescription = "minivan"
        )
        Column() {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 8.dp, top = 8.dp, start = 8.dp, end = 8.dp),
                text = "Citroen C4 Picasso",
                color = Color.White,
                fontSize = 24.sp
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 8.dp, top = 8.dp, start = 8.dp, end = 8.dp),
                text = "Kia Carnival",
                color = Color.White,
                fontSize = 24.sp
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 8.dp, top = 8.dp, start = 8.dp, end = 8.dp),
                text = "Ford Galaxy",
                color = Color.White,
                fontSize = 24.sp
            )

            if (dialogState.value) {
                MinivanCars(
                    dialogState, navController = navController,
                    name.toString(),
                    phone.toString(),
                    from.toString(),
                    to.toString(),
                    date.toString(),
                    time.toString(),
                    passengers.toString()
                )
            }
            TextButton(
                onClick = {
                    dialogState.value = true
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically),
                    text = "Другие машины в этом тарифе",
                    color = Color.White
                )
            }
            Button(modifier = Modifier
                .padding(bottom = 4.dp, top = 8.dp, start = 8.dp, end = 8.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.5f),
                colors = ButtonDefaults.buttonColors(DarkYellow),
                onClick = {
                    navController.navigate(
                        "final_screen/" +
                                "${name.toString()}/" +
                                "${phone.toString()}/" +
                                "${from.toString()}/" +
                                "${to.toString()}/" +
                                "${date.toString()}/" +
                                "${time.toString()}/" +
                                "${passengers.toString()}/" +
                                "MINIVAN"
                    )
                }
            ) {
                Text(
                    text = "Выбрать тариф",
                    color = Color.Black
                )
            }
            Button(
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .fillMaxWidth(0.25f)
                    .align(Alignment.CenterHorizontally),
                enabled = true,
                colors = ButtonDefaults.buttonColors(DarkRed),
                onClick = {
                    navController.navigateUp()
                }
            ) {
                Text(
                    text = "Назад",
                    color = Color.Black
                )
            }
        }
    }
}