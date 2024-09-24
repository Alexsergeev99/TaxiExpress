package ru.alexsergeev.express.screens

import android.content.pm.ActivityInfo
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.express.R
import ru.alexsergeev.express.buttons.CounterPassengersButton
import ru.alexsergeev.express.buttons.IconControlButton
import ru.alexsergeev.express.components.AddressPointsInMainScreen
import ru.alexsergeev.express.components.MainScreenButton
import ru.alexsergeev.express.ui.theme.DarkRed
import ru.alexsergeev.express.ui.theme.DarkYellow
import ru.alexsergeev.express.utils.LockScreenOrientation
import ru.alexsergeev.express.utils.MaskVisualTransformation
import ru.alexsergeev.express.viewmodel.OrderViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun MainPage(
    navController: NavController,
    viewModel: OrderViewModel = koinViewModel()
) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    val order by viewModel.getOrder().collectAsStateWithLifecycle()

    var pickedDate by rememberSaveable {
        mutableStateOf(LocalDate.now())
    }
    var pickedTime by rememberSaveable {
        mutableStateOf("")
    }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("MMM dd yyyy")
                .format(pickedDate)
        }
    }

    val focusManager = LocalFocusManager.current
    val ctx = LocalContext.current
    val mask = MaskVisualTransformation("##:##")

    val dateDialogState = rememberMaterialDialogState()

    var valueCounter by rememberSaveable {
        mutableStateOf(1)
    }

    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black)
    ) {
        IconButton(modifier = Modifier
            .padding(top = 72.dp, start = 32.dp)
            .size(32.dp),
            onClick = {
                navController.navigate("left_menu")
            }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_density_medium_24),
                contentDescription = "menu",
                tint = Color.White
            )
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.Center)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(180.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.slavlogonew),
                contentDescription = "test image"
            )
            AddressPointsInMainScreen()
            IconControlButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                icon = Icons.Outlined.Check,
                contentDescription = "clear cursor",
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
                            text = "Выбрать дату",
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
                    OutlinedTextField(
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally),
                        value = order.time,
                        shape = RoundedCornerShape(20),
                        label = { Text(text = "Время поездки") },
                        placeholder = { Text(text = "##:##") },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            focusedContainerColor = Color.Black,
                            unfocusedTextColor = Color.White,
                            unfocusedContainerColor = Color.Black
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            viewModel.setOrder(
                                order.copy(time = it)
                            )
                            pickedTime = it
                        },
                        visualTransformation = mask
                    )
                    IconControlButton(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        icon = Icons.Outlined.Check,
                        contentDescription = "clear cursor",
                        onClick = { focusManager.clearFocus() },
                        tintColor = Color.White
                    )
                }
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
                    viewModel.setOrder(
                        order.copy(countPassengers = valueCounter)
                    )
                },
                onValueIncreaseClick = {
                    valueCounter++
                    viewModel.setOrder(
                        order.copy(countPassengers = valueCounter)
                    )
                },
                onValueClearClick = {
                    valueCounter = 1
                    viewModel.setOrder(
                        order.copy(countPassengers = valueCounter)
                    )
                },
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(Color.Black)
            )
            MainScreenButton(navController, valueCounter, pickedDate, pickedTime)
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
            colors = DatePickerDefaults.colors(
                Color.Black, Color.White, Color.White, Color.Black, Color.Black, DarkYellow,
                Color.White
            ),
            initialDate = LocalDate.now(),
            title = "Дата поездки",
        ) {
            pickedDate = it
        }
    }
}