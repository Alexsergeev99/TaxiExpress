package ru.alexsergeev.express.screens

import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePickerColors
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import ru.alexsergeev.express.R
import ru.alexsergeev.express.buttons.CounterPassengersButton
import ru.alexsergeev.express.buttons.IconControlButton
import ru.alexsergeev.express.ui.theme.DarkRed
import ru.alexsergeev.express.ui.theme.DarkYellow
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(navController: NavController, name: String?, phone: String?) {
    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    var pickedTime by remember {
        mutableStateOf("")
    }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("MMM dd yyyy")
                .format(pickedDate)
        }
    }
//    val formattedTime by remember {
//        derivedStateOf {
//            DateTimeFormatter
//                .ofPattern("hh:mm")
//                .format(pickedTime)
//        }
//    }

    val focusManager = LocalFocusManager.current
    val ctx = LocalContext.current
    val mask = MaskVisualTransformation("##:##")

    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()

    val start = remember {
        mutableStateOf("")
    }
    val finish = remember {
        mutableStateOf("")
    }
    var valueCounter by remember {
        mutableStateOf(1)
    }
    val passengers = remember {
        mutableStateOf("1")
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
                        unfocusedLabelColor = Color.White,
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onValueChange = {
                        start.value = it
//                        focusManager.clearFocus()
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
//                        focusManager.clearFocus()
                    }
                )
            }
            IconControlButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                icon = Icons.Outlined.Check,
                contentDescription = "Decrease count",
                onClick = { focusManager.clearFocus() },
                tintColor = Color.White
            )
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
//                    Button(modifier = Modifier
//                        .padding(4.dp)
//                        .fillMaxWidth(),
//                        colors = ButtonDefaults.buttonColors(DarkRed),
//                        onClick = {
//                            timeDialogState.show()
//                        }) {
//                        Text(
//                            text = "Время поездки",
//                            color = Color.Black
//                        )
//                    }
                    OutlinedTextField(
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically),
                        value = pickedTime.toString(),
                        shape = RoundedCornerShape(20),
                        label = { Text(text = "Время поездки") },
                        placeholder = { Text(text = "##:##") } ,
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            focusedContainerColor = Color.Black,
                            unfocusedTextColor = Color.White,
                            unfocusedContainerColor = Color.Black
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            pickedTime = it
                        },
                        visualTransformation = mask
                    )
//                    Button(
//                        modifier = Modifier
//                            .padding(4.dp)
//                            .fillMaxWidth(),
//                        enabled = true,
//                        colors = ButtonDefaults.buttonColors(DarkRed),
//                        onClick = { /*TODO*/ }) {
//                        Text(
//                            text = if(pickedTime != null) {
//                                "${pickedTime[0]}" + "${pickedTime[1]}" + ":" + "${pickedTime[2]}" + "${pickedTime[3]}"
//                            } else {
//                                   pickedTime
//                            },
//                            color = Color.Black
//                        )
//                    }
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 4.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                text = "Количество пассажиров",
                color = Color.White
            )
            CounterPassengersButton(
                value = valueCounter.toString(),
                onValueDecreaseClick = {
                    valueCounter = maxOf(valueCounter - 1, 1)
                    passengers.value = valueCounter.toString()
                },
                onValueIncreaseClick = {
                    valueCounter++
                    passengers.value = valueCounter.toString()
                },
                onValueClearClick = {
                    valueCounter = 1
                    passengers.value = valueCounter.toString()
                },
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(Color.Black)
            )
            Button(modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(4.dp)
                .fillMaxWidth(0.5f),
                colors = ButtonDefaults.buttonColors(DarkYellow),
                onClick = {
                    if(start.value.isNotEmpty() && finish.value.isNotEmpty() && pickedDate >= LocalDate.now() && pickedTime.isNotEmpty()) {
                        navController.navigate(
                            "rate_screen/" +
                                    "${name.toString()}/" +
                                    "${phone.toString()}/" +
                                    "${start.value.toString()}/" +
                                    "${finish.value.toString()}/" +
                                    "${pickedDate.toString()}/" +
                                    "${pickedTime.toString()}/${passengers.value.toString()}"

                        )
                    } else if (start.value.isNotEmpty() && finish.value.isNotEmpty()){
                        Toast.makeText(ctx, "Дата поездки не может быть раньше сегодняшнего дня", Toast.LENGTH_SHORT)
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
    MaterialDialog(
        backgroundColor = Color.Black,
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = "OK")
            negativeButton(text = "Назад")
        }) {
        datepicker(
            colors = DatePickerDefaults.colors(Color.Black, Color.White,Color.White,Color.Black,Color.Black,DarkYellow,
                Color.White),
            initialDate = LocalDate.now(),
            title = "Дата поездки",
        ) {
            pickedDate = it
        }
    }
//    MaterialDialog(
//        dialogState = timeDialogState,
//        buttons = {
//            positiveButton(text = "OK")
//            negativeButton(text = "Назад")
//        }) {
//        timepicker(
//            initialTime = LocalTime.now(),
//            title = "Время поездки",
//        ) {
//            pickedTime = it
//        }
//    }
}

class MaskTimeVisualTransformation(private val mask: String): VisualTransformation {
    private val specialSymbolsIndices = mask.indices.filter { mask[it] != '#' }

    override fun filter(text: AnnotatedString): TransformedText {
        var out = ""
        var maskIndex = 0
        text.forEach { char ->
            while (specialSymbolsIndices.contains(maskIndex)) {
                out += mask[maskIndex]
                maskIndex++
            }
            out += char
            maskIndex++
        }
        return TransformedText(AnnotatedString(out), offsetTranslator())
    }

    private fun offsetTranslator() = object: OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            val offsetValue = offset.absoluteValue
            if (offsetValue == 0) return 0
            var numberOfHashtags = 0
            val masked = mask.takeWhile {
                if (it == '#') numberOfHashtags++
                numberOfHashtags < offsetValue
            }
            return masked.length + 1
        }

        override fun transformedToOriginal(offset: Int): Int {
            return mask.take(offset.absoluteValue).count { it == '#' }
        }
    }
}