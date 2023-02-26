package com.winter.chatsystem.data

import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.winter.chatsystem.components.BottomNavBar

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
        topBar = {
            Text(
                text = "Home",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
        },
        content = { paddingValues ->

            LazyColumn(
                contentPadding = paddingValues,
                content = {
                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("chat/1")
                                    }
                                )
                        ) {
                            Icon(
                                Icons.Filled.Person,
                                contentDescription = "person1",
                                modifier = Modifier
                                    .padding(end = 12.dp)
                                    .size(40.dp)
                            )
                            Column(
                                content = {
                                    Text(
                                        text = "Talal",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        modifier = Modifier
                                    )
                                    Text(
                                        text = "Online",
                                        fontWeight = FontWeight.Thin,
                                        fontSize = 14.sp,
                                        modifier = Modifier
                                    )
                                }
                            )
                        }
                    }
                }
            )
        },
        bottomBar = { BottomNavBar(navController) },
    )
}