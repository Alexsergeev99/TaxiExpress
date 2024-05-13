package ru.alexsergeev.express

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ru.alexsergeev.express.ui.theme.ExpressTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpressTheme {
                Registration()
            }
        }
    }
}

@Composable
fun Registration() {
    val name = remember {
        mutableStateOf("")
    }
    val phone = remember {
        mutableStateOf("")
    }
    val ctx = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black), contentAlignment = Alignment.Center
    )
    {
        Image(
            modifier = Modifier
                .fillMaxWidth()
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
                color = Color.White,
                text = "Регистрация"
            )
            OutlinedTextField(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),
                value = name.value,
                label = { Text(text = "Ваше имя") },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    focusedContainerColor = Color.Black,
                    unfocusedTextColor = Color.White,
                    unfocusedContainerColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onValueChange = {
                    name.value = it
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),
                value = phone.value,
                label = { Text(text = "Ваш номер телефона") },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    focusedContainerColor = Color.Black,
                    unfocusedTextColor = Color.White,
                    unfocusedContainerColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                onValueChange = {
                    phone.value = it
                }
            )
            Button(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.5f)
                    .align(alignment = Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(Color.Red),
                onClick = {
                    if (phone.value.isNotEmpty()) {
                        if (android.util.Patterns.PHONE.matcher(phone.value).matches()) {
                            Toast.makeText(ctx, "Phone Number is valid..", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(ctx, "Phone Number is invalid..", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        Toast.makeText(ctx, "Please enter phone number", Toast.LENGTH_SHORT).show()
                    }
                }) {
                Text(text = "Войти")
            }
        }
    }
}

@Composable
fun MainPage() {

}