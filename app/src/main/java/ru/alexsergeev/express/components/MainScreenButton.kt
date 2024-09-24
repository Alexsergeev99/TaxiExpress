package ru.alexsergeev.express.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.express.ui.theme.DarkYellow
import ru.alexsergeev.express.viewmodel.OrderViewModel
import java.time.LocalDate

@Composable
fun MainScreenButton(
    navController: NavController,
    valueCounter: Int,
    pickedDate: LocalDate,
    pickedTime: String,
    viewModel: OrderViewModel = koinViewModel()
) {

    val order by viewModel.getOrder().collectAsStateWithLifecycle()
    val ctx = LocalContext.current

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(modifier = Modifier
            .align(alignment = Alignment.CenterHorizontally)
            .padding(4.dp)
            .fillMaxWidth(0.5f),
            colors = ButtonDefaults.buttonColors(DarkYellow),
            onClick = {
                if (order.from.isNotEmpty() &&
                    order.to.isNotEmpty() &&
                    pickedDate >= LocalDate.now() &&
                    pickedTime.isNotEmpty() &&
                    pickedTime[0].toString().toInt() <= 2 &&
                    pickedTime[2].toString().toInt() <= 5 &&
                    valueCounter <= 7
                ) {
                    viewModel.setOrder(
                        order.copy(
                            time = "${pickedDate.toString()[8]}${pickedDate.toString()[9]}.${pickedDate.toString()[5]}${pickedDate.toString()[6]}.${pickedDate.toString()[0]}${pickedDate.toString()[1]}${pickedDate.toString()[2]}${pickedDate.toString()[3]} ${pickedTime[0]}${pickedTime[1]}:${pickedTime[2]}${pickedTime[3]}"
                        )
                    )
                    navController.navigate(
                        "rate_screen"
                    )
                } else if (pickedTime[0].toString().toInt() > 2 || pickedTime[2].toString()
                        .toInt() > 5
                ) {
                    Toast.makeText(
                        ctx,
                        "Пожалуйста, укажите корректное время поездки",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else if (valueCounter > 7) {
                    Toast.makeText(
                        ctx,
                        "Максимальное количество пассажиров - 7",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else if (pickedDate < LocalDate.now()) {
                    Toast.makeText(
                        ctx,
                        "Дата поездки не может быть раньше сегодняшнего дня",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    Toast.makeText(
                        ctx,
                        "Пожалуйста, заполните все поля",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        ) {
            Text(
                text = "Далее",
                color = Color.Black
            )
        }
    }
}