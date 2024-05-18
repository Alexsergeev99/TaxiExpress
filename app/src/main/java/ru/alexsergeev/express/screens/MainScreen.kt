package ru.alexsergeev.express.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import ru.alexsergeev.express.R
import ru.alexsergeev.express.ui.theme.DarkRed
import ru.alexsergeev.express.ui.theme.DarkYellow
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(navController: NavController, name: String?) {
    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    var pickedTime by remember {
        mutableStateOf(LocalTime.NOON)
    }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("MMM dd yyyy")
                .format(pickedDate)
        }
    }
    val formattedTime by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("hh:mm")
                .format(pickedTime)
        }
    }

    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()

    val start = remember {
        mutableStateOf("")
    }
    val finish = remember {
        mutableStateOf("")
    }
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black)
    ) {
        IconButton(modifier = Modifier
            .padding(top = 32.dp, start = 32.dp)
            .size(32.dp),
            onClick = {
                navController.navigate("left_menu/${name.toString()}")
            }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_density_medium_24),
                contentDescription = "menu",
                tint = Color.White
            )
        }
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .size(200.dp)
                .padding(top = 16.dp)
                .align(alignment = Alignment.TopCenter),
            painter = painterResource(id = R.drawable.slavexpresslogo__horisontal_),
            contentDescription = "test image"
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.Center)
        ) {
            Row {
                OutlinedTextField(modifier = Modifier
                    .align(alignment = Alignment.Bottom)
                    .fillMaxWidth(0.5f),
                    shape = RoundedCornerShape(20),
                    value = start.value,
                    label = { Text(text = "Откуда поедем") },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        focusedContainerColor = Color.Black,
                        unfocusedTextColor = Color.White,
                        unfocusedContainerColor = Color.Black,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onValueChange = {
                        start.value = it
                    }
                )
                OutlinedTextField(
                    modifier = Modifier
                        .align(alignment = Alignment.Bottom)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20),
                    value = finish.value,
                    label = { Text(text = "Куда поедем") },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        focusedContainerColor = Color.Black,
                        unfocusedTextColor = Color.White,
                        unfocusedContainerColor = Color.Black,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onValueChange = {
                        finish.value = it
                    }
                )
            }
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Column {
                    Button(modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(0.5f),
                        colors = ButtonDefaults.buttonColors(DarkRed),
                        onClick = {
                            dateDialogState.show()
                        }) {
                        Text(
                            text = "Дата поездки",
                            color = Color.Black
                        )
                    }
                    Button(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(0.5f),
                        enabled = true,
                        colors = ButtonDefaults.buttonColors(DarkRed),
                        onClick = { /*TODO*/ }) {
                        Text(
                            text = formattedDate,
                            color = Color.Black
                        )
                    }
                }
                Column {
                    Button(modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(DarkRed),
                        onClick = {
                            timeDialogState.show()
                        }) {
                        Text(
                            text = "Время поездки",
                            color = Color.Black
                        )
                    }
                    Button(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(),
                        enabled = true,
                        colors = ButtonDefaults.buttonColors(DarkRed),
                        onClick = { /*TODO*/ }) {
                        Text(
                            text = formattedTime,
                            color = Color.Black
                        )
                    }
                }
            }
            Button(modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(4.dp)
                .fillMaxWidth(0.5f),
                colors = ButtonDefaults.buttonColors(DarkYellow),
                onClick = {
                    navController.navigate("rate_screen/${name.toString()}")
                }) {
                Text(
                    text = "Далее",
                    color = Color.Black
                )
            }
        }
    }
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = "OK")
            negativeButton(text = "Назад")
        }) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Дата поездки",
        ) {
            pickedDate = it
        }
    }
    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton(text = "OK")
            negativeButton(text = "Назад")
        }) {
        timepicker(
            initialTime = LocalTime.now(),
            title = "Время поездки",
        ) {
            pickedTime = it
        }
    }
//}
//    )
}