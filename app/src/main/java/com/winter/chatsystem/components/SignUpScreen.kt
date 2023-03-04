package com.winter.chatsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavHostController
) {

    var userName by remember { mutableStateOf (TextFieldValue("")) }
    var email by remember { mutableStateOf (TextFieldValue("")) }
    var passwordCreation by remember { mutableStateOf (TextFieldValue("")) }
    var passwordConfirmation by remember { mutableStateOf (TextFieldValue("")) }
    var passwordVisibility by remember { mutableStateOf(false) }

    val auth = Firebase.auth

    var emailError = false
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
            .background(MaterialTheme.colorScheme.background),
        //contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 36.sp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 90.dp)
        )
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp),
            ) {
                OutlinedTextField(
                    value = userName,
                    onValueChange = { userName = it },
                    label = { Text("Username", color = MaterialTheme.colorScheme.onSurfaceVariant) },
                    modifier = Modifier.fillMaxWidth(.8f),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                    ),
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp),
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email", color = MaterialTheme.colorScheme.onSurfaceVariant) },
                    modifier = Modifier.fillMaxWidth(.8f),
                    maxLines = 2,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                    ),
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp),
            ) {
                OutlinedTextField(
                    value = passwordCreation,
                    onValueChange = { passwordCreation = it },
                    label = {
                        Text(
                            "Password",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    modifier = Modifier.fillMaxWidth(.8f),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                    ),
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

            Row(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp),
            ) {
                OutlinedTextField(
                    value = passwordConfirmation,
                    onValueChange = { passwordConfirmation = it },
                    label = {
                        Text(
                            "Confirm password",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    modifier = Modifier.fillMaxWidth(.8f),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                    ),
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
            Spacer(modifier = Modifier.fillMaxSize(0.10f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                FloatingActionButton(onClick = { /*TODO*/ },
                    containerColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth(0.8f)) {
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
            Text(text = "Already have an account?", modifier = Modifier.padding(top = 4.8.dp))
            TextButton(onClick = {
                navController.navigate("login")
            },
                modifier = Modifier.padding(bottom = 0.dp),
                contentPadding = PaddingValues(bottom = 15.dp)
            ) {
                Text(text = "Sign In", color = MaterialTheme.colorScheme.primary)
            }

        }
    }
}