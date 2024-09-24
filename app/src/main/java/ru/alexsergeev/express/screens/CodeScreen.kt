package ru.alexsergeev.express.screens

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.text.TextUtils
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import ru.alexsergeev.express.R
import ru.alexsergeev.express.components.OtpTextField
import ru.alexsergeev.express.ui.theme.DarkYellow
import ru.alexsergeev.express.utils.LockScreenOrientation


@Composable
fun CodeScreen(
    navController: NavController,
    verificationID: String?
) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    val codeValue = rememberSaveable {
        mutableStateOf("")
    }

    val message = rememberSaveable {
        mutableStateOf("")
    }

    var mAuth: FirebaseAuth = FirebaseAuth.getInstance();

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
        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Text(
                modifier = Modifier
                    .padding(20.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                color = Color.White,
                text = "Введите код",
                fontSize = 24.sp
            )
            OtpTextField(
                otpText = codeValue.value,
                onOtpTextChange = { value, otpInputFilled ->
                    codeValue.value = value
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Button(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.5f)
                    .align(alignment = Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(DarkYellow),
                onClick = {
                    focusManager.clearFocus()
                    if (TextUtils.isEmpty(codeValue.value.toString())) {
                        Toast.makeText(ctx, "Пожалуйста, введите код из СМС", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
                            verificationID.toString(), codeValue.value.toString()
                        )
                        signInWithPhoneAuthCredential(
                            credential,
                            mAuth,
                            ctx as Activity,
                            ctx,
                            message
                        )
                        navController.navigate("main_screen")
                    }
                }
            ) {
                Text(
                    text = "Войти",
                    color = Color.Black
                )
            }
        }
    }
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
                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    // когда неуспешна
                    Toast.makeText(
                        context,
                        "Неправильный код",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
}