package ru.alexsergeev.express.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.express.ui.theme.DarkYellow
import ru.alexsergeev.express.viewmodel.OrderViewModel

@Composable
fun MainInfoBoxInFinalScreen(separateDateTime: List<String?>, viewModel: OrderViewModel = koinViewModel()) {

    val order by viewModel.getOrder().collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .border(4.dp, DarkYellow)
    ) {
        Column {
            Row {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically),
                    color = Color.White,
                    text = "Имя: ${order.user.name}"
                )
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically),
                    color = Color.White,
                    text = "Номер телефона: +7${order.user.number}"
                )
            }
            Row {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically),
                    color = Color.White,
                    text = "Поедем из: ${order.from}"
                )
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically),
                    color = Color.White,
                    text = "Поедем в: ${order.to}"
                )
            }
            Row {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically),
                    color = Color.White,
                    text = "Дата поездки: ${separateDateTime[0]}"
                )
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically),
                    color = Color.White,
                    text = "Время поездки: ${separateDateTime[1]}"
                )
            }
            Row {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically),
                    color = Color.White,
                    text = "Тариф: ${order.tariff}"
                )
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically),
                    color = Color.White,
                    text = "Количество пассажиров: ${order.countPassengers}"
                )
            }
        }
    }
}