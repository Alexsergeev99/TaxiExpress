package ru.alexsergeev.express.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.express.viewmodel.OrderViewModel

@Composable
fun AddressPointsInMainScreen(
    viewModel: OrderViewModel = koinViewModel()
) {

    val order by viewModel.getOrder().collectAsStateWithLifecycle()

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(modifier = Modifier
            .align(alignment = Alignment.CenterVertically)
            .fillMaxWidth(0.5f),
            shape = RoundedCornerShape(20),
            value = order.from,
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
                viewModel.setOrder(
                    order.copy(from = it)
                )
            }
        )
        OutlinedTextField(
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .fillMaxWidth(),
            shape = RoundedCornerShape(20),
            value = order.to,
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
                viewModel.setOrder(
                    order.copy(to = it)
                )
            }
        )
    }
}