package ru.alexsergeev.express.rates

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.alexsergeev.express.R
import ru.alexsergeev.express.ui.theme.DarkYellow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MinivanRate(navController: NavController,
                name: String?,
                phone: String?,
                from: String?,
                to: String?,
                date: String?,
                time: String?,
                passengers: String?) {

    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)

    val sheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 24.dp, top = 8.dp, start = 8.dp, end = 8.dp)
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(bottom = 24.dp, top = 8.dp, start = 8.dp, end = 8.dp)
                .size(200.dp)
//                .clip(CircleShape)
                .align(alignment = Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.mers_minivan),
            contentDescription = "minivan"
        )
        Column() {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 24.dp, top = 8.dp, start = 8.dp, end = 8.dp),
                text = "Citroen C4 Picasso",
                color = Color.White,
                fontSize = 24.sp
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 24.dp, top = 8.dp, start = 8.dp, end = 8.dp),
                text = "Kia Carnival",
                color = Color.White,
                fontSize = 24.sp
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 24.dp, top = 8.dp, start = 8.dp, end = 8.dp),
                text = "Ford Galaxy",
                color = Color.White,
                fontSize = 24.sp
            )

            TextButton(
                onClick = {
//                    navController.navigate("minivans/${name.toString()}")
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically),
                    text = "еще",
                    color = Color.White
                )
            }
            Button(modifier = Modifier
                .padding(bottom = 32.dp, top = 8.dp, start = 8.dp, end = 8.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.5f),
                colors = ButtonDefaults.buttonColors(DarkYellow),
                onClick = {
                    navController.navigate("final_screen/" +
                            "${name.toString()}/" +
                            "${phone.toString()}/" +
                            "${from.toString()}/" +
                            "${to.toString()}/" +
                            "${date.toString()}/" +
                            "${time.toString()}/" +
                            "${passengers?.toString()}/" +
                            "Минивен")
                }
            ) {
                Text(
                    text = "Выбрать тариф",
                    color = Color.Black
                )
            }
        }
    }
}