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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.winter.chatsystem.R


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
    var currentUser = auth.currentUser
    val email = currentUser?.email
    val displayName = currentUser?.displayName


    Scaffold(
        content = { paddingV ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(start = 16.dp, end = 16.dp, top = paddingV.calculateTopPadding())
                    .padding(bottom = paddingV.calculateBottomPadding())
            ) {
                Divider(
                    color = MaterialTheme.colorScheme.primary,
                    thickness = 2.dp,
                    modifier = Modifier
                        .padding(horizontal = 0.dp, vertical = 3.dp)
                )

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
                                //label = { Text(text = "Username", color = MaterialTheme.colorScheme.primary) },
                                placeholder = { Text(displayName.toString())},
                                singleLine = true,
                                readOnly = true,
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
                                //label = { Text(text = "Email", color = MaterialTheme.colorScheme.primary) },
                                placeholder = { Text(email.toString())},
                                singleLine = true,
                                readOnly = true,
                                modifier = Modifier
                                    .fillMaxWidth(),
                            )
                        }
                    }

                    item {
                        /*Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(top = 13.dp, bottom = 13.dp)
                        ) {
                            FloatingActionButton(
                                onClick = { /*TODO*/ },
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .size(45.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.Edit),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier
                                )
                            }
                        }

                         */
                        Row(
                            modifier = Modifier
                                .padding(bottom = 13.dp)
                        ) {
                            FloatingActionButton(
                                onClick = {
                                    navController.navigate("login")


                                },
                                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .size(45.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.Logout),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,

                                )
                            }
                        }
                    }
                }
            }
        },
        //bottomBar = { BottomNavBar(navController) },
    )
}