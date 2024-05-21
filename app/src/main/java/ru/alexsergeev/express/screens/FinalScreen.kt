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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.alexsergeev.express.R
import ru.alexsergeev.express.ui.theme.DarkRed
import ru.alexsergeev.express.ui.theme.DarkYellow

@Composable
fun FinalScreen(
    navController: NavController,
    name: String?,
    phone: String?,
    from: String?,
    to: String?,
    date: String?,
    time: String?,
    passengers: Int?,
    rate: String?
) {
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
            Box(modifier = Modifier
                .border(4.dp, DarkRed)) {
                Column {
                    Row {
                        Text(modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically),
                            color = Color.White,
                            text = "Имя: ${name.toString()}")
                        Text(modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically),
                            color = Color.White,
                            text = "Номер телефона: ${phone.toString()}")
                    }
                    Row {
                        Text(modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically),
                            color = Color.White,
                            text = "Поедем из: ${from.toString()}")
                        Text(modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically),
                            color = Color.White,
                            text = "Поедем в: ${to.toString()}")
                    }
                    Row {
                        Text(modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically),
                            color = Color.White,
                            text = "Дата поездки: ${date.toString()}")
                        Text(modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically),
                            color = Color.White,
                            text = "Время поездки: ${time.toString()}")
                    }
                    Row {
                        Text(modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically),
                            color = Color.White,
                            text = "Тариф: ${rate.toString()}")
                        Text(modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically),
                            color = Color.White,
                            text = "Количество пассажиров: ${passengers.toString()}")
                    }
                }
            }

            OutlinedTextField(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
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
                    .padding(16.dp)
                    .fillMaxWidth(0.5f)
                    .align(alignment = Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(DarkYellow),
                onClick = {
                    focusManager.clearFocus()
//                    navController.navigate("code_screen/${name.toString()}")
                }) {
                Text(text = "Рассчитать заказ")
            }
        }
    }
}