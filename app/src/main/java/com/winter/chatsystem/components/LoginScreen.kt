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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var checked by remember {
        mutableStateOf(true)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        IconButton(
            onClick = { navController.popBackStack() }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = ("ArrowBack"),
                tint = MaterialTheme.colorScheme.secondary,
                
            )
        }
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