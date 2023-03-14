package com.winter.chatsystem.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val auth = Firebase.auth
    val user = auth.currentUser
    val email = user?.email
    val displayName = user?.displayName

    Scaffold(
        content = {
            LazyColumn(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp,
                        top = 90.dp,
                        bottom = 56.dp
                    ),
                content = {
                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                //.padding(start = 12.dp, end = 12.dp, top = paddingValues.calculateBottomPadding(), bottom = paddingValues.calculateBottomPadding())
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("chat/1")
                                    }
                                )
                        ) {
                            Icon(
                                Icons.Filled.Person,
                                contentDescription = "user",
                                modifier = Modifier
                                    .padding(end = 12.dp)
                                    .size(40.dp)
                            )
                            Column(
                                content = {
                                    Text(
                                        text = user?.displayName.toString(),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier
                                    )
                                    Text(
                                        text = user?.email.toString(),
                                        fontWeight = FontWeight.Light,
                                        fontSize = 14.sp,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier
                                    )
                                }
                            )
                        }
                    }
                }
            )
        },
        //bottomBar = { BottomNavBar(navController) },
    )
}