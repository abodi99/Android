package com.winter.chatsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.winter.chatsystem.ui.theme.ChatSystemTheme

@Composable
fun SignUpScreen() {

    var userName by remember { mutableStateOf (TextFieldValue("")) }
    var email by remember { mutableStateOf (TextFieldValue("")) }
    var passwordCreation by remember { mutableStateOf (TextFieldValue("")) }
    var passwordConfirmation by remember { mutableStateOf (TextFieldValue("")) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize(0.27f)
            ) {

                Text(

                    text = "Sign Up",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary


                )
            }

            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("Username", color = MaterialTheme.colorScheme.onSurfaceVariant) },
                modifier = Modifier.fillMaxWidth(.8f),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(textColor = MaterialTheme.colorScheme.onPrimary,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                    )
            )


            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", color = MaterialTheme.colorScheme.onSurfaceVariant) },
                modifier = Modifier.fillMaxWidth(.8f),
                maxLines = 3,
                colors = TextFieldDefaults.outlinedTextFieldColors(textColor = MaterialTheme.colorScheme.onPrimary,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                )            )

            OutlinedTextField(
                value = passwordCreation,
                onValueChange = { passwordCreation = it },
                label = { Text("Password", color = MaterialTheme.colorScheme.onSurfaceVariant) },
                modifier = Modifier.fillMaxWidth(.8f),
                singleLine = true,
                placeholder = { Text(text = "Do your homework") },
                colors = TextFieldDefaults.outlinedTextFieldColors(textColor = MaterialTheme.colorScheme.onPrimary,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                )
            )

            OutlinedTextField(
                value = passwordConfirmation,
                onValueChange = { passwordConfirmation = it },
                label = { Text("Confirm password", color = MaterialTheme.colorScheme.onSurfaceVariant) },
                modifier = Modifier.fillMaxWidth(.8f),
                singleLine = true,
                placeholder = { Text(text = "Do your homework") },
                colors = TextFieldDefaults.outlinedTextFieldColors(textColor = MaterialTheme.colorScheme.onPrimary,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                )
            )
        }

    }

}


@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    ChatSystemTheme {
        SignUpScreen()
    }
}