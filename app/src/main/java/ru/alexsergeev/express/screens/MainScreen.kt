package ru.alexsergeev.express.screens

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage() {
    var pickedDate by  remember {
        mutableStateOf(LocalDate.now())
    }
    var pickedTime by  remember {
        mutableStateOf(LocalTime.NOON)
    }
    val formattedDate by  remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("MMM dd yyyy")
                .format(pickedDate)
        }
    }
    val formattedTime by  remember {
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
            .background(color = Color.Red)
        , contentAlignment = Alignment.TopCenter
        ) {
        Column(modifier = Modifier
            .padding(16.dp)) {
            Row {
                OutlinedTextField(modifier = Modifier
                    .align(alignment = Alignment.Bottom)
                    .fillMaxWidth(0.5f)
                    ,shape = RoundedCornerShape(20)
                    ,value = start.value
                    ,label = { Text(text = "Откуда поедем") },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        focusedContainerColor = Color.Black,
                        unfocusedTextColor = Color.White,
                        unfocusedContainerColor = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    ,onValueChange = {
                    start.value = it
                }
                )
                OutlinedTextField(
                    modifier = Modifier
                        .align(alignment = Alignment.Bottom)
                        .fillMaxWidth()
                    ,shape = RoundedCornerShape(20)
                    ,value = finish.value
                    ,label = { Text(text = "Куда поедем") },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        focusedContainerColor = Color.Black,
                        unfocusedTextColor = Color.White,
                        unfocusedContainerColor = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    ,onValueChange = {
                        finish.value = it
                    }
                )
            }
            Row(modifier = Modifier
                .padding(16.dp)) {
                Column {
                    Button(modifier = Modifier
//                        .align(alignment = Alignment.Bottom)
                        .padding(4.dp)
                        .fillMaxWidth(0.5f),
                        colors = ButtonDefaults.buttonColors(Color.Blue)
                        ,onClick = {
                            dateDialogState.show()
                        }) {
                        Text(text = "Нажмите, чтобы выбрать дату поездки")
                    }
                    Text(modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(0.5f)
                        , text = formattedDate)
                }
                Column {
                    Button(modifier = Modifier
//                        .align(alignment = Alignment.Bottom)
                        .padding(4.dp)
                        .fillMaxWidth()
                        ,colors = ButtonDefaults.buttonColors(Color.Blue)
                        ,onClick = {
                            timeDialogState.show()
                        }) {
                        Text(text = "Нажмите, чтобы выбрать время поездки")
                    }
                    Text(modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        ,text = formattedTime)
                }
            }
            Button(modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(4.dp)
                .fillMaxWidth(0.5f)
                ,colors = ButtonDefaults.buttonColors(Color.Blue)
                ,onClick = { /*TODO*/ }) {
                Text(text = "Далее")
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

@Composable
fun DatePickerDemo(context: Context) {
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val now = Calendar.getInstance()
    mYear = now.get(Calendar.YEAR)
    mMonth = now.get(Calendar.MONTH)
    mDay = now.get(Calendar.DAY_OF_MONTH)
    now.time = Date()
    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val cal = Calendar.getInstance()
            cal.set(year, month, dayOfMonth)
            date.value = SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.getDefault())
                .format(Date(cal.time.toString()))
        }, mYear, mMonth, mDay
    )

    val day1= Calendar.getInstance()
    day1.set(Calendar.DAY_OF_MONTH, 1)
    datePickerDialog.datePicker.minDate = day1.timeInMillis
    datePickerDialog.datePicker.maxDate = Calendar.getInstance().timeInMillis
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            datePickerDialog.show()
        }) {
            Text(text = "Click To Open Date Picker")
        }
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = "Selected date: ${date.value}")
    }
}