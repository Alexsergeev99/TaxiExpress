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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.alexsergeev.express.ui.theme.DarkRed

private val cars = listOf("Kia Cerato", "Kia K5", "Kia Seltos",
    "Kia Sportage", "Mazda 6", "Mazda CX-5", "Mercedes-Benz C-klasse",
    "Nissan Juke", "Nissan Teana", "Nissan Qashqai", "Opel Insignia", "Renault Talisman",
    "Skoda Octavia", "Subaru Legacy", "Toyota RAV 4", "Toyota Prius", "Volkswagen Passat",
    "Volkswagen Tiguan", "Volvo S80", "Volvo XC60", "Volvo XC90",
    "Geely Coolray", "Genesis G70", "Honda Accord", "Hyundai Elantra",
    "Kaiyi E5", "Chevrolet Malibu", "Chery Arrizo 8")

@Composable
fun ComfortCars(
    dialogState: MutableState<Boolean>,
    navController: NavController,
    name: String?,
    phone: String?,
    from: String?,
    to: String?,
    date: String?,
    time: String?,
    passengers: String?
) {
    val dialogText = remember {
        mutableStateOf("")
    }
    AlertDialog(onDismissRequest = {
        dialogState.value = false
    }, modifier = Modifier
        .padding(bottom = 8.dp),
        confirmButton = {
            TextButton(onClick = {
                navController.navigate(
                    "final_screen/" +
                            "${name.toString()}/" +
                            "${phone.toString()}/" +
                            "${from.toString()}/" +
                            "${to.toString()}/" +
                            "${date.toString()}/" +
                            "${time.toString()}/" +
                            "${passengers?.toString()}/" +
                            "Комфорт"
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
                    .padding(bottom = 4.dp),
//            .background(Color.White)
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                items(cars.size) { car ->
                    Text(
                        modifier = Modifier
                            .padding(4.dp), fontSize = 12.sp,
                        text = cars[car],
                        color = Color.Black
                    )
                }
            }
        }
    )
}