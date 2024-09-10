package ru.alexsergeev.express.cars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.express.ui.theme.DarkRed
import ru.alexsergeev.express.ui.theme.DarkYellow
import ru.alexsergeev.express.viewmodel.OrderViewModel

private val cars = listOf("Citroen C4 Grand Picasso", "Volkswagen Caddy")

@Composable
fun MinivanCars(
    dialogState: MutableState<Boolean>,
    navController: NavController,
    viewModel: OrderViewModel = koinViewModel()
) {
    val order by viewModel.getOrder().collectAsStateWithLifecycle()

    AlertDialog(onDismissRequest = {
        dialogState.value = false
    }, modifier = Modifier
        .padding(bottom = 16.dp),
        confirmButton = {
            TextButton(
                onClick = {
                    viewModel.setOrder(
                        order.copy(tariff = "MINIVAN")
                    )
                    navController.navigate("final_screen/")
                    dialogState.value = false
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkYellow,
                    contentColor = Color.Black,
                    disabledContentColor = Color.Black
                )
            ) {
                Text(text = "Выбрать этот тариф")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    dialogState.value = false
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkRed,
                    contentColor = Color.Black,
                    disabledContentColor = Color.Black
                )
            ) {
                Text(text = "Закрыть")
            }
        },
        title = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                items(cars.size) { car ->
                    Text(
                        modifier = Modifier
                            .padding(16.dp), fontSize = 16.sp,
                        text = cars[car],
                        color = Color.Black
                    )
                }
            }
        }
    )
}