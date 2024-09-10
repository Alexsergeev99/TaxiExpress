package ru.alexsergeev.express.screens

import android.app.Activity
import android.content.pm.ActivityInfo
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.express.utils.LockScreenOrientation
import ru.alexsergeev.express.R
import ru.alexsergeev.express.buttons.IconControlButton
import ru.alexsergeev.express.dto.User
import ru.alexsergeev.express.ui.theme.DarkYellow
import ru.alexsergeev.express.utils.MaskVisualTransformation
import ru.alexsergeev.express.viewmodel.OrderViewModel
import java.util.concurrent.TimeUnit

@Composable
fun Registration(navController: NavController, viewModel: OrderViewModel = koinViewModel()) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    val order by viewModel.getOrder().collectAsStateWithLifecycle()

    val verificationID = rememberSaveable {
        mutableStateOf("1")
    }

    val message = rememberSaveable {
        mutableStateOf("")
    }

    val mAuth: FirebaseAuth = FirebaseAuth.getInstance();
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    val ctx = LocalContext.current
    val focusManager = LocalFocusManager.current
    val mask = MaskVisualTransformation("+7 (###) ### ##-##")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .clip(RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center
    )
    {
        Column {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(200.dp)
                    .padding(bottom = 48.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.slavexpresslogo__horisontal_),
                contentDescription = "test image"
            )
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                color = Color.White,
                text = "Регистрация",
                fontSize = 24.sp
            )
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally), horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically),
                    value = order.user.name,
                    shape = RoundedCornerShape(20),
                    label = { Text(text = "Ваше имя") },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        focusedContainerColor = Color.Black,
                        unfocusedTextColor = Color.White,
                        unfocusedContainerColor = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onValueChange = {
                        viewModel.setOrder(
                            order.copy(user = User(it, order.user.number))
                        )
                    }
                )
                IconControlButton(
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    icon = Icons.Outlined.Check,
                    contentDescription = "clear cursor",
                    onClick = { focusManager.clearFocus() },
                    tintColor = Color.White
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally), horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically),
                    value = order.user.number,
                    shape = RoundedCornerShape(20),
                    label = { Text(text = "Ваш номер телефона") },
                    placeholder = { Text(text = "+7 (###) ### ##-##") },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        focusedContainerColor = Color.Black,
                        unfocusedTextColor = Color.White,
                        unfocusedContainerColor = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    onValueChange = {
                        viewModel.setOrder(
                            order.copy(user = User(order.user.name, it))
                        )
                    },
                    visualTransformation = mask
                )
                IconControlButton(
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    icon = Icons.Outlined.Check,
                    contentDescription = "clear cursor",
                    onClick = { focusManager.clearFocus() },
                    tintColor = Color.White
                )
            }
            Button(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.5f)
                    .align(alignment = Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(DarkYellow),
                onClick = {
                    focusManager.clearFocus()
                    if (order.user.number.isNotEmpty() && order.user.name.isNotEmpty()) {
                        if (Patterns.PHONE.matcher(order.user.number).matches() &&
                            order.user.number[0].toString().toInt() == 9 &&
                            order.user.number.length == 10
                        ) {
                            Toast.makeText(ctx, "Номер телефона указан верно", Toast.LENGTH_SHORT)
                                .show()
                            val number = "+7${order.user.number}"
                            sendVerificationCode(number, mAuth, ctx as Activity, callbacks)
                            Log.d("button", verificationID.value)
                        } else {
                            Toast.makeText(ctx, "Номер телефона указан неверно", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        Toast.makeText(
                            ctx,
                            "Пожалуйста, не оставляйте поле пустым",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }) {
                Text(
                    text = "Получить код",
                    color = Color.Black
                )
            }
        }
    }
    callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            message.value = "Верификация прошла успешно"
            Toast.makeText(ctx, "Верификация успешна", Toast.LENGTH_SHORT).show()
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            message.value = "Ошибка верификации пользователя : \n" + p0.message
            Toast.makeText(ctx, "Верификация неуспешна", Toast.LENGTH_SHORT).show()
        }

        override fun onCodeSent(verificationId: String, p1: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(verificationId, p1)
            verificationID.value = verificationId
            Log.d("code", verificationID.value)
            navController.navigate("code_screen/${verificationID.value.toString()}")
        }
    }
}
private fun sendVerificationCode(
    number: String,
    auth: FirebaseAuth,
    activity: Activity,
    callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
) {
    val options = PhoneAuthOptions.newBuilder(auth)
        .setPhoneNumber(number)
        .setTimeout(60L, TimeUnit.SECONDS)
        .setActivity(activity)
        .setCallbacks(callbacks)
        .build()
    PhoneAuthProvider.verifyPhoneNumber(options)
}