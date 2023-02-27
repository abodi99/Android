package com.winter.chatsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.winter.chatsystem.ui.theme.ChatSystemTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen() {

    var userName by remember { mutableStateOf (TextFieldValue("")) }
    var email by remember { mutableStateOf (TextFieldValue("")) }
    var passwordCreation by remember { mutableStateOf (TextFieldValue("")) }
    var passwordConfirmation by remember { mutableStateOf (TextFieldValue("")) }

    var newEmail by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    val auth = Firebase.auth
/*
    Box(contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back", tint = Color.Black)
    }
    
 */
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        /*Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "back",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(60.dp)
                .padding(15.dp)
                .clickable(onClick = {})

        )
         */

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth(0.30f)
                    .fillMaxHeight(0.15f)
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
                colors = TextFieldDefaults.outlinedTextFieldColors(textColor = MaterialTheme.colorScheme.onPrimary,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                )
            )
            Spacer(modifier = Modifier.fillMaxSize(0.10f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                FloatingActionButton(onClick = { /*TODO*/ },
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
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxSize()
            ) {
            Row(modifier = Modifier.padding(bottom = 7.dp)) {
                Text(text = "Already have an account?", modifier = Modifier.padding(top = 4.8.dp))
                TextButton(onClick = { /*TODO*/ },
                    modifier = Modifier.padding(bottom = 0.dp),
                    contentPadding = PaddingValues(bottom = 15.dp)
                ) {
                    Text(text = "Sign In", color = MaterialTheme.colorScheme.primary)
                }

            }
        }

    }
}