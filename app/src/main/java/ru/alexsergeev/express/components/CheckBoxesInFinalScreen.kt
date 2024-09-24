package ru.alexsergeev.express.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.express.dto.Options
import ru.alexsergeev.express.viewmodel.OrderViewModel

@Composable
fun CheckBoxesInFinalScreen(
    viewModel: OrderViewModel = koinViewModel()
) {

    val order by viewModel.getOrder().collectAsStateWithLifecycle()

    Row {
        Checkbox(
            checked = order.options.babySeat,
            modifier = Modifier.padding(16.dp),
            onCheckedChange = {
                viewModel.setOrder(
                    order.copy(
                        options = Options(it, order.options.withAnimal)
                    )
                )
            },
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
            checked = order.options.withAnimal,
            modifier = Modifier.padding(16.dp),
            onCheckedChange = {
                viewModel.setOrder(
                    order.copy(
                        options = Options(it, order.options.withAnimal)
                    )
                )
            },
        )
        Text(
            text = "Буду с питомцем",
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.CenterVertically),
            color = Color.White
        )
    }
}