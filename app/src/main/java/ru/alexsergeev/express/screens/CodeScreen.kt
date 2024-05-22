package ru.alexsergeev.express.screens

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import ru.alexsergeev.express.R
import ru.alexsergeev.express.ui.theme.DarkRed
import ru.alexsergeev.express.ui.theme.DarkYellow

//@Composable
//fun OTPAuth() {
//    val mAuth = FirebaseAuth.getInstance()
//    var verificationOtp = ""
//val turnOffPhoneVerify = FirebaseAuth.getInstance().firebaseAuthSettings
//    .setAppVerificationDisabledForTesting(false)
//
//    fun send(phone: String) {
//    val options = PhoneAuthOptions.newBuilder(mAuth)
//
//        .setPhoneNumber("+7$phone")
//        .setTimeout(60L, TimeUnit.SECONDS)
//        .setActivity(this)
//        .setCallbacks(object :
//            PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
//                Toast.makeText(applicationContext, "Verification Completed", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onVerificationFailed(p0: FirebaseException) {
//                Toast.makeText(applicationContext, "Verification Failed", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onCodeSent(otp: String, p1: PhoneAuthProvider.ForceResendingToken) {
//                super.onCodeSent(otp, p1)
//                verificationOtp = otp
//                Toast.makeText(applicationContext, "Otp Send Successfully", Toast.LENGTH_SHORT).show()
//            }
//        }).build()
//    PhoneAuthProvider.verifyPhoneNumber(options)
//}
//
// fun otpVerification(otp: String) {
//    val credential = PhoneAuthProvider.getCredential(verificationOtp, otp)
//    FirebaseAuth.getInstance().signInWithCredential(credential)
//        .addOnCompleteListener(this) { task ->
//            if (task.isSuccessful) {
//                Toast.makeText(applicationContext, "Verification Successful", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(applicationContext, "Wrong Otp", Toast.LENGTH_SHORT).show()
//            }
//        }
//}
//}

@Composable
fun CodeScreen(navController: NavController, name: String?, phone: String?) {

    val phoneNumber = remember {
        mutableStateOf("")
    }

    val codeValue = remember {
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
                    .padding(20.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                color = Color.White,
                text = "Введите код"
            )
            OtpTextField(
                otpText = codeValue.value,
                onOtpTextChange = { value, otpInputFilled ->
                    codeValue.value = value
                }
            )
            Button(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.5f)
                    .align(alignment = Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(DarkYellow),
                onClick = {
                    focusManager.clearFocus()
                    if (codeValue.value.isNotEmpty()) {
                        if (codeValue.value.length == 6) {
                            Toast.makeText(ctx, "Код введен верно", Toast.LENGTH_SHORT)
                                .show()
                            navController.navigate("main_screen/${name.toString()}/${phone.toString()}")
                        } else {
                            Toast.makeText(ctx, "Код введен неверно", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        Toast.makeText(ctx, "Пожалуйста, введите код из СМС", Toast.LENGTH_SHORT)
                            .show()
                    }
//                    if (TextUtils.isEmpty(codeValue.value.toString())) {
//                        // displaying toast message on below line.
//                        Toast.makeText(ctx, "Пожалуйста, введите код из СМС", Toast.LENGTH_SHORT)
//                            .show()
//                    } else {
//                        // on below line generating phone credentials.
//                        val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
//                            verificationID.value, codeValue.value
//                        )
//                        // on below line signing within credentials.
//                        signInWithPhoneAuthCredential(
//                            credential,
//                            mAuth,
//                            ctx as Activity,
//                            ctx,
//                            message
//                        )
//                    }
                }
            ) {
                Text(text = "Войти")
            }
        }
    }
    callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            // ниже обновление сообщения и показ тоаста
            message.value = "Верификация прошла успешно"
            Toast.makeText(ctx, "Верификация неуспешна", Toast.LENGTH_SHORT).show()
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            // тоаст с ошибкой
            message.value = "Ошибка верификации пользователя : \n" + p0.message
            Toast.makeText(ctx, "Верификация неуспешна", Toast.LENGTH_SHORT).show()
        }

        override fun onCodeSent(verificationId: String, p1: PhoneAuthProvider.ForceResendingToken) {
            // метод отправки кода
            super.onCodeSent(verificationId, p1)
            verificationID.value = verificationId
        }
    }
}

@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = 6,
    onOtpTextChange: (String, Boolean) -> Unit
) {
    LaunchedEffect(Unit) {
        if (otpText.length > otpCount) {
            throw IllegalArgumentException("Количество символов не может быть больше $otpCount")
        }
    }

    BasicTextField(
        modifier = modifier,
        value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
        onValueChange = {
            if (it.text.length <= otpCount) {
                onOtpTextChange.invoke(it.text, it.text.length == otpCount)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(otpCount) { index ->
                    CharView(
                        index = index,
                        text = otpText
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    )
}

@Composable
private fun CharView(
    index: Int,
    text: String
) {
    val isFocused = text.length == index
    val char = when {
        index == text.length -> "0"
        index > text.length -> ""
        else -> text[index].toString()
    }
    Text(
        text = char,
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .border(
                1.dp, when {
                    isFocused -> DarkRed
                    else -> DarkYellow
                }, RoundedCornerShape(8.dp)
            )
            .padding(2.dp),
        style = MaterialTheme.typography.headlineMedium,
        color = if (isFocused) {
            Color.White
        } else {
            Color.White
        },
        textAlign = TextAlign.Center
    )
}

private fun signInWithPhoneAuthCredential(
    credential: PhoneAuthCredential,
    auth: FirebaseAuth,
    activity: Activity,
    context: Context,
    message: MutableState<String>
) {
    auth.signInWithCredential(credential)
        .addOnCompleteListener(activity) { task ->
            // когда верификация успешна
            if (task.isSuccessful) {
                message.value = "Успешно"
                Toast.makeText(context, "Успешно", Toast.LENGTH_SHORT).show()
            } else {
                // Sign in failed, display a message
                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    // когда неуспешна
                    Toast.makeText(
                        context,
                        "Неправильный код" + (task.exception as FirebaseAuthInvalidCredentialsException).message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
}