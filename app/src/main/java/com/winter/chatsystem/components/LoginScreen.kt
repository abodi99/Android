package com.winter.chatsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    val auth = Firebase.auth

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
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
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 16.dp)
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
                        .fillMaxWidth(),
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
                        .fillMaxWidth(),
                )
            }
            val isChecked = remember { mutableStateOf(false) }

            Checkbox(
                checked = isChecked.value,
                onCheckedChange = {
                    isChecked.value = it
                },
                enabled = true,
                modifier = modifier
            )

            Spacer(modifier = Modifier.fillMaxSize(0.10f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                FloatingActionButton(onClick = {
                    auth.signInWithEmailAndPassword(email.text, password.text)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                navController.navigate("chat")
                            } else {
                                println("Failed to navigate to chat")
                            }
                        }
                },
                    containerColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth(0.65f)) {
                    Text(
                        text = "Sign Up",
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
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(bottom = 0.dp),
                contentPadding = PaddingValues(bottom = 15.dp)
            ) {
                Text(text = "Sign up", color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}