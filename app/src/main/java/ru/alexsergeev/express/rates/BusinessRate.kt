package ru.alexsergeev.express.rates

import android.content.pm.ActivityInfo
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.express.LockScreenOrientation
import ru.alexsergeev.express.R
import ru.alexsergeev.express.cars.BusinessCars
import ru.alexsergeev.express.ui.theme.DarkRed
import ru.alexsergeev.express.ui.theme.DarkYellow
import ru.alexsergeev.express.viewmodel.OrderViewModel

@Composable
fun BusinessRate(
    navController: NavController,
    viewModel: OrderViewModel = koinViewModel()
) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    val dialogState = rememberSaveable {
        mutableStateOf(false)
    }
    val order by viewModel.getOrder().collectAsStateWithLifecycle()

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
                .clip(CircleShape)
                .align(alignment = Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.maybach),
            contentDescription = "maybach"
        )
        Column {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 8.dp, top = 8.dp, start = 8.dp, end = 8.dp),
                text = "Mercedes-Benz S-Class",
                color = Color.White,
                fontSize = 20.sp
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 8.dp, top = 8.dp, start = 8.dp, end = 8.dp),
                text = "Audi A8",
                color = Color.White,
                fontSize = 20.sp
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 8.dp, top = 8.dp, start = 8.dp, end = 8.dp),
                text = "BMW 7-Series",
                color = Color.White,
                fontSize = 20.sp
            )

            if (dialogState.value) {
                BusinessCars(
                    dialogState, navController = navController
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
                    viewModel.setOrder(
                        order.copy(tariff = "BUSINESS")
                    )
                    navController.navigate(
                        "final_screen/"
//                                +
//                                "BUSINESS"
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