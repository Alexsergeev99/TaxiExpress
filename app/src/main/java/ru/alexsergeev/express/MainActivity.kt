package ru.alexsergeev.express

import android.os.Bundle
import android.text.Editable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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
        mutableStateOf("Name")
    }
    val phone = remember {
        mutableStateOf("Phone")
    }
    
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            ,contentAlignment = Alignment.Center)
        {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp)
                    .align(alignment = Alignment.TopCenter)
                ,painter = painterResource(id = R.drawable.slavexpresslogo__horisontal_)
                ,contentDescription = "test image")
            Column {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.CenterHorizontally)
                ,color = Color.White
                ,text = "Регистрация"
            )
            TextField(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                ,value = name.value,
                onValueChange = {
                    name.value = it
                }
            )
            TextField(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                ,value = phone.value,
                onValueChange = {
                    phone.value = it
                }
            )
            Button(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.5f)
                    .align(alignment = Alignment.CenterHorizontally)
                ,colors = ButtonDefaults.buttonColors(Color.Red)
                ,onClick = { /*TODO*/ }) {
                Text(text = "Войти")
            }
        }
    }
}