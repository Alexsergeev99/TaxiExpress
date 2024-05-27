package ru.alexsergeev.express.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.alexsergeev.express.R
import ru.alexsergeev.express.buttons.IconControlButton
import ru.alexsergeev.express.dto.Options
import ru.alexsergeev.express.dto.Order
import ru.alexsergeev.express.dto.User
import ru.alexsergeev.express.ui.theme.DarkRed
import ru.alexsergeev.express.ui.theme.DarkYellow
import ru.alexsergeev.express.viewmodel.OrderViewModel

@Composable
fun FinalScreen(
    navController: NavController,
    name: String?,
    phone: String?,
    from: String?,
    to: String?,
    date: String?,
    time: String?,
    passengers: String?,
    rate: String?,
    vm: OrderViewModel = viewModel()
) {
    val ctx = LocalContext.current
    val response = remember {
        mutableStateOf("")
    }
    val comment = remember {
        mutableStateOf("")
    }
    val focusManager = LocalFocusManager.current
    val checkedChildrenState = remember {
        mutableStateOf(false)
    }
    val checkedPetState = remember {
        mutableStateOf(false)
    }
    val order = Order(
        from = from.toString(),
        to = to.toString(),
        time = "${date.toString()} ${time.toString()}",
        tariff = rate.toString(),
        countPassengers = passengers.toString().toInt(),
        comment = comment.value.toString(),
        user = User(name.toString(), "+7${phone.toString()}"),
        options = Options(checkedChildrenState.value)
    )
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black), contentAlignment = Alignment.Center
    )
    {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .size(180.dp)
                .padding(top = 48.dp)
                .align(alignment = Alignment.TopCenter),
            painter = painterResource(id = R.drawable.slavexpresslogo__horisontal_),
            contentDescription = "test image"
        )
        Column {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                fontSize = 24.sp,
                color = DarkYellow,
                text = "Подтверждение заказа"
            )
            Box(
                modifier = Modifier
                    .border(4.dp, DarkYellow)
            ) {
                Column {
                    Row {
                        Text(
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.CenterVertically),
                            color = Color.White,
                            text = "Имя: ${name.toString()}"
                        )
                        Text(
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.CenterVertically),
                            color = Color.White,
                            text = "Номер телефона: +7${phone.toString()}"
                        )
                    }
                    Row {
                        Text(
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.CenterVertically),
                            color = Color.White,
                            text = "Поедем из: ${from.toString()}"
                        )
                        Text(
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.CenterVertically),
                            color = Color.White,
                            text = "Поедем в: ${to.toString()}"
                        )
                    }
                    Row {
                        Text(
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.CenterVertically),
                            color = Color.White,
                            text = "Дата поездки: ${date.toString()}"
                        )
                        Text(
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.CenterVertically),
                            color = Color.White,
                            text = "Время поездки: ${time.toString()}"
                        )
                    }
                    Row {
                        Text(
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.CenterVertically),
                            color = Color.White,
                            text = "Тариф: ${rate.toString()}"
                        )
                        Text(
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.CenterVertically),
                            color = Color.White,
                            text = "Количество пассажиров: ${passengers.toString()}"
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(top = 8.dp),
                    value = comment.value,
                    shape = RoundedCornerShape(20),
                    label = { Text(text = "Комментарий:") },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        focusedContainerColor = Color.Black,
                        unfocusedTextColor = Color.White,
                        unfocusedContainerColor = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onValueChange = {
                        comment.value = it
                    }
                )
                IconControlButton(
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    icon = Icons.Outlined.Check,
                    contentDescription = "Decrease count",
                    onClick = { focusManager.clearFocus() },
                    tintColor = Color.White
                )
            }
            Row {
                Checkbox(
                    checked = checkedChildrenState.value,
                    modifier = Modifier.padding(16.dp),
                    onCheckedChange = { checkedChildrenState.value = it },
                )
                Text(
                    text = "Нужно детское кресло",
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.CenterVertically),
                    color = Color.White
                )
            }
            Row {
                Checkbox(
                    checked = checkedPetState.value,
                    modifier = Modifier.padding(16.dp),
                    onCheckedChange = { checkedPetState.value = it },
                )
                Text(
                    text = "Буду с питомцем",
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.CenterVertically),
                    color = Color.White
                )
            }
            Button(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(0.5f)
                    .align(alignment = Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(DarkYellow),
                onClick = {
                    focusManager.clearFocus()
//                    coroutineScope.launch {
                    vm.makeOrder(order)
//                    }
//                    postDataUsingRetrofit(
//                        ctx, response, order
//                    )
                    navController.navigate("after_screen/${name.toString()}")
                }) {
                Text(
                    text = "Рассчитать заказ",
                    color = Color.Black
                )
            }
            Button(
                modifier = Modifier
                    .padding(bottom = 8.dp, top = 4.dp)
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

//fun postDataUsingRetrofit(
//    ctx: Context,
//    result: MutableState<String>,
//    order: Order
//) {
//    val order: Order = order
//    val call: Call<Order> = OrderApi.retrofitService.makeOrder(order)
//    call!!.enqueue(object : Callback<Order?> {
//        override fun onResponse(call: Call<Order?>?, response: Response<Order?>) {
//            Toast.makeText(ctx, "Data posted to API", Toast.LENGTH_SHORT).show()
////            val model: Order? = response.body()
//            val resp = "Response Code : " + response.code()
//            result.value = resp
//        }
//
//        override fun onFailure(call: Call<Order?>?, t: Throwable) {
//            result.value = "Error found is : " + t.message
//        }
//    })
//}
//}