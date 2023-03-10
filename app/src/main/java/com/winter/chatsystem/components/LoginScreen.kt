package com.winter.chatsystem.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {


    val context = LocalContext.current

    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    var passwordVisibility by remember { mutableStateOf(false) }

    val auth = Firebase.auth

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 36.sp,
            modifier = modifier
                .align(Alignment.TopCenter)
                .padding(top = 90.dp)
        )
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = modifier
                    .padding(top = 10.dp, bottom = 10.dp),
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { input ->
                        email = input
                    },
                    label = { Text(text = "Email") },
                    modifier = modifier
                        .fillMaxWidth(0.8f),
                )
            }
            Row(
                modifier = modifier
                    .padding(top = 10.dp, bottom = 10.dp),
            ) {
                OutlinedTextField(
                    value = password,
                    onValueChange = { input ->
                        password = input
                    },
                    label = { Text(text = "Password") },
                    modifier = modifier
                        .fillMaxWidth(0.8f),
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                            Icon(
                                imageVector = if (passwordVisibility) Icons.Filled.Info else Icons.Default.Face,
                                contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                            )
                        }
                    }
                )
            }
            val isChecked = remember { mutableStateOf(false) }

            /*Checkbox(
                checked = isChecked.value,
                onCheckedChange = {
                    isChecked.value = it
                },
                enabled = true,
                modifier = modifier
            )*/

            Spacer(modifier = Modifier.fillMaxSize(0.10f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                FloatingActionButton(onClick = {
                    val emailText = email.text.toString()
                    val passwordText = password.text.toString()
                    if (emailText.isNotEmpty() && passwordText.isNotEmpty()) {

                        auth.signInWithEmailAndPassword(email.text, password.text)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    navController.navigate("home")
                                } else {
                                    println("Failed to navigate to chat")
                                }
                            }
                    }
                    else {
                        Toast.makeText(context, "make sure to enter an email and a password", Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                    containerColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth(0.8f)) {
                    Text(
                        text = "Sign In",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary

                    )
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)

        ) {
            Text(text = "Don't have an account?", modifier = Modifier.padding(top = 5.9.dp))
            TextButton(
                onClick = {
                    navController.navigate("signup")
                },
                modifier = Modifier.padding(bottom = 0.dp),
                contentPadding = PaddingValues(bottom = 15.dp)
            ) {
                Text(text = "Sign up", color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}