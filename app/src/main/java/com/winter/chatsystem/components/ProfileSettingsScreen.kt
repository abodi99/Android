package com.winter.chatsystem.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountSettingsScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    var newEmail by remember { mutableStateOf(TextFieldValue("")) }
    var newDisplayName by remember { mutableStateOf(TextFieldValue("")) }

    val auth = Firebase.auth
    val currentUser = auth.currentUser
    var email = currentUser?.email
    var displayName = currentUser?.displayName


    Scaffold(
        content = { paddingV ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .padding(bottom = paddingV.calculateBottomPadding())
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = ("ArrowBack"),
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .clickable(
                            onClick = { navController.navigate("settings") }
                        )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,//Keep text centered
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                ) {
                    //Spacer(modifier = modifier.weight(1f))
                    Text(
                        text = "Profile Settings",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center,
                        fontSize = 36.sp,
                    )
                }

                LazyColumn(
                    modifier = Modifier

                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 100.dp, bottom = 60.dp)
                                .border(
                                    0.8.dp,
                                    MaterialTheme.colorScheme.outline,
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .clickable(
                                    onClick = {
                                        // Change picture
                                    }
                                ),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primaryContainer,
                                modifier = Modifier
                                    .size(200.dp)
                            )
                        }
                    }
                    item {
                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp),
                        ) {
                            OutlinedTextField(
                                value = newDisplayName,
                                onValueChange = { input ->
                                    newDisplayName = input
                                },
                                label = { Text(text = "Username", color = MaterialTheme.colorScheme.primary) },
                                placeholder = { Text(displayName.toString())},
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                    }
                    item {
                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp),
                        ) {
                            OutlinedTextField(
                                value = newEmail,
                                onValueChange = { input ->
                                    newEmail = input
                                },
                                label = { Text(text = "Email", color = MaterialTheme.colorScheme.primary) },
                                placeholder = { Text(email.toString())},
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth(),
                            )
                        }
                    }
                }
            }
        },
        bottomBar = { BottomNavBar(navController) },
    )
}