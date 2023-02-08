package com.winter.chatsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 32.dp)
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = modifier
                    
            )
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

            Button(
                modifier = modifier
                    .padding(top = 30.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = modifier
                )
            }
        }
    }
}