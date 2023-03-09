package com.winter.chatsystem.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.winter.chatsystem.EmailPasswordActivity
import com.winter.chatsystem.classes.LoginError
import com.winter.chatsystem.classes.isValidEmail
import com.winter.chatsystem.classes.isValidPassword

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavHostController
) {
    val context = LocalContext.current
    var userName by remember { mutableStateOf(TextFieldValue("")) }
    var emailAddress by remember { mutableStateOf(TextFieldValue("")) }
    var passwordCreation by remember { mutableStateOf(TextFieldValue("")) }
    var passwordConfirmation by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisibility by remember { mutableStateOf(false) }

    val auth = FirebaseAuth.getInstance()

    var usernameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var loginError by remember { mutableStateOf<LoginError?>(null) }

    fun signup(userName: String, email: String, password: String) {
        if (email.isEmpty()) {
            loginError = LoginError.EmptyEmail
        } else if (!isValidEmail(email)) {
            loginError = LoginError.InvalidEmail
        } else if (password.isEmpty()) {
            loginError = LoginError.EmptyPassword
        } else if (!isValidPassword(password)) {
            loginError = LoginError.WeakPassword
        } else if (passwordCreation != passwordConfirmation) {
            loginError = LoginError.nonMatchPassword
        } else {
            auth.fetchSignInMethodsForEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val signInMethods = task.result?.signInMethods
                        if (signInMethods?.isNotEmpty() == true) {
                            // Email already exists
                            loginError = LoginError.EmailAlreadyExists
                        } else {
                            // Email doesn't exist, create new user account
                            auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        // User account creation is successful
                                        println("User account creation is successful")

                                        //auth.currentUser?.sendEmailVerification()
                                        val user = auth.currentUser
                                        val profileUpdates = UserProfileChangeRequest.Builder()
                                            .setDisplayName(userName)
                                            .build()

                                        user?.updateProfile(profileUpdates)
                                    } else {
                                        // User account creation failed
                                        println("User account creation failed")
                                    }
                                }
                        }
                    } else {
                        // Failed to fetch sign-in methods for email
                        println("Failed to fetch sign-in methods for email")
                    }
                }
        }

    }


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
                    onValueChange = { newUserName ->
                        userName = newUserName
                        usernameError = ""
                    },
                    label = {
                        Text(
                            "Username",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    modifier = Modifier.fillMaxWidth(.8f),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                    ),
                    isError = usernameError.isNotEmpty()
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp),
            ) {
                OutlinedTextField(
                    value = emailAddress,
                    onValueChange = { emailAddress = it },
                    label = { Text("Email", color = MaterialTheme.colorScheme.onSurfaceVariant) },
                    modifier = Modifier.fillMaxWidth(.8f),
                    maxLines = 2,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
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
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
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
                    },
                    isError = (passwordCreation != passwordConfirmation)
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
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
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
                    },
                    isError = (passwordCreation != passwordConfirmation)
                )
            }
            Spacer(modifier = Modifier.fillMaxSize(0.10f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                FloatingActionButton(
                    onClick = {
                        when (loginError) {
                            LoginError.EmptyEmail -> {
                                // Display "Please enter an email address" error message
                                println("Please enter an email address")
                            }
                            LoginError.InvalidEmail -> {
                                // Display "Please enter a valid email address" error message
                                println("Please enter a valid email address")
                            }
                            LoginError.EmptyPassword -> {
                                // Display "Please enter a password" error message
                                println("Please enter a password")
                            }
                            LoginError.WeakPassword -> {
                                // Display "Please enter a stronger password" error message
                                println("Please enter a stronger password")

                            }
                            LoginError.nonMatchPassword -> {
                                println("Password doesn't match'")
                            }
                            LoginError.EmailAlreadyExists -> {
                                println("Email already in use")

                            }
                            LoginError.Unknown -> {
                                // Display "An unknown error occurred" error message
                                println("An unknown error occurred")
                            }
                            null -> {
                                // No error occurred, continue with login logic
                                signup(userName.text, emailAddress.text, passwordCreation.text)
                            }
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    if (loginError == LoginError.EmailAlreadyExists) {
                        Toast.makeText(
                            context,
                            "This email address is already registered",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (loginError == LoginError.EmptyPassword) {
                        Toast.makeText(context, "please enter a password", Toast.LENGTH_SHORT)
                            .show()
                    } else if (loginError == LoginError.InvalidEmail) {
                        Toast.makeText(context, "please enter a valid email", Toast.LENGTH_SHORT)
                            .show()
                    } else if (loginError == LoginError.EmptyEmail) {
                        Toast.makeText(context, "please enter an email", Toast.LENGTH_SHORT).show()
                    } else if (loginError == LoginError.nonMatchPassword) {
                        Toast.makeText(context, "nonMatchPassword", Toast.LENGTH_SHORT).show()
                    }

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
            TextButton(
                onClick = {
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



