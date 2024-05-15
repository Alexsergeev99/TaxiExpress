package ru.alexsergeev.express.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
fun MainPage() {
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
            .fillMaxHeight(0.4f)
    ) {
//        Image(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 4.dp)
//            .align(alignment = Alignment.TopCenter),
//        painter = painterResource(id = R.drawable.slavexpresslogo__horisontal_),
//        contentDescription = "test image")
        Column(
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.BottomCenter)
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
                        focusedContainerColor = DarkYellow,
                        unfocusedTextColor = Color.White,
                        unfocusedContainerColor = DarkYellow
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
                        focusedContainerColor = DarkYellow,
                        unfocusedTextColor = Color.White,
                        unfocusedContainerColor = DarkYellow
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
                        colors = ButtonDefaults.buttonColors(DarkYellow), onClick = {
                            dateDialogState.show()
                        }) {
                        Text(text = "Дата поездки")
                    }
//                    Text(
//                        modifier = Modifier
//                            .padding(4.dp)
//                            .fillMaxWidth(0.5f)
//                        , text = formattedDate
//                    )
                    Button(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(0.5f),
                        enabled = true,
                        colors = ButtonDefaults.buttonColors(DarkYellow),
                        onClick = { /*TODO*/ }) {
                        Text(text = formattedDate)
                    }
                }
                Column {
                    Button(modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(DarkYellow),
                        onClick = {
                            timeDialogState.show()
                        }) {
                        Text(text = "Время поездки")
                    }
//                    Text(
//                        modifier = Modifier
//                            .padding(4.dp)
//                            .fillMaxWidth()
//                        , text = formattedTime
//                    )
                    Button(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(),
                        enabled = true,
                        colors = ButtonDefaults.buttonColors(DarkYellow),
                        onClick = { /*TODO*/ }) {
                        Text(text = formattedTime)
                    }
                }
            }
            Button(modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(4.dp)
                .fillMaxWidth(0.5f),
                colors = ButtonDefaults.buttonColors(DarkYellow),
                onClick = { /*TODO*/ }) {
                Text(text = "Далее",
                    color = Color.Black)
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
    }
}