package ru.alexsergeev.express.cars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.express.ui.theme.DarkRed
import ru.alexsergeev.express.viewmodel.OrderViewModel

private val cars = listOf("Hongqi H5", "Hongqi H9", "Mercedes-Benz Maybach S-klasse", "Zeekr 009")

@Composable
fun BusinessCars(
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
            TextButton(onClick = {
                viewModel.setOrder(
                    order.copy(tariff = "BUSINESS")
                )
                navController.navigate(
                    "final_screen/"
//                            +
//                            "Бизнес"
                )
                dialogState.value = false
            },
                colors = ButtonDefaults.buttonColors(contentColor =  Color.Black, disabledContentColor = Color.Black)
            ) {
                Text(text = "Выбрать этот тариф")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                dialogState.value = false
            },
                colors = ButtonDefaults.buttonColors(containerColor = DarkRed, contentColor =  Color.Black, disabledContentColor = Color.Black)
            ) {
                Text(text = "Закрыть")
            }
        },
        title = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                items(cars.size) { car ->
                    Text(
                        modifier = Modifier
                            .padding(8.dp), fontSize = 14.sp,
                        text = cars[car],
                        color = Color.Black
                    )
                }
            }
        }
    )
}