package ru.alexsergeev.express.screens

import android.app.Activity
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import ru.alexsergeev.express.R
import ru.alexsergeev.express.ui.theme.DarkYellow
import java.util.concurrent.TimeUnit
import kotlin.math.absoluteValue

@Composable
fun Registration(navController: NavController) {
    val name = remember {
        mutableStateOf("")
    }
    val phone = remember {
        mutableStateOf("")
    }
    val verificationID = remember {
        mutableStateOf("")
    }

    val message = remember {
        mutableStateOf("")
    }

//    var mAuth: FirebaseAuth = FirebaseAuth.getInstance();
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    val ctx = LocalContext.current
    val focusManager = LocalFocusManager.current
    val mask = MaskVisualTransformation("+7 (###) ### ##-##")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black), contentAlignment = Alignment.Center
    )
    {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .size(240.dp)
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
                text = "Регистрация",
                fontSize = 24.sp
            )
            OutlinedTextField(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),
                value = name.value,
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
                    name.value = it
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),
                value = phone.value,
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
                    phone.value = it
                },
                visualTransformation = mask
            )
            Button(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.5f)
                    .align(alignment = Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(DarkYellow),
                onClick = {
                    focusManager.clearFocus()
                    if (phone.value.isNotEmpty() && name.value.isNotEmpty()) {
                        if (Patterns.PHONE.matcher(phone.value).matches()) {
                            Toast.makeText(ctx, "Номер телефона указан верно", Toast.LENGTH_SHORT)
                                .show()
//                            sendVerificationCode(phone.value, mAuth, ctx as Activity, callbacks)
                            navController.navigate("code_screen/${name.value.toString()}/${phone.value.toString()}")
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
                Text(text = "Получить код",
                    color = Color.Black)
            }
        }
    }
    callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            // обновление сообщения и отправка тоаста
            message.value = "Верификация прошла успешно"
            Toast.makeText(ctx, "Верификация неуспешна", Toast.LENGTH_SHORT).show()
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            // тоаст с ошибкой
            message.value = "Fail to verify user : \n" + p0.message
            Toast.makeText(ctx, "Верификация неуспешна", Toast.LENGTH_SHORT).show()
        }

        override fun onCodeSent(verificationId: String, p1: PhoneAuthProvider.ForceResendingToken) {
            // отправка кода
            super.onCodeSent(verificationId, p1)
            verificationID.value = verificationId
        }
    }
}

class MaskVisualTransformation(private val mask: String) : VisualTransformation {
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

    private fun offsetTranslator() = object : OffsetMapping {
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

// метод для получения кода
private fun sendVerificationCode(
    number: String,
    auth: FirebaseAuth,
    activity: Activity,
    callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
) {
    // опции кода
    val options = PhoneAuthOptions.newBuilder(auth)
        .setPhoneNumber(number) // номер телефона
        .setTimeout(60L, TimeUnit.SECONDS) // время
        .setActivity(activity)
        .setCallbacks(callbacks)
        .build()
    PhoneAuthProvider.verifyPhoneNumber(options)
}