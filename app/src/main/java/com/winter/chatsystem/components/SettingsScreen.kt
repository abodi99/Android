package com.winter.chatsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.winter.chatsystem.settingsText
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.runtime.*
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier

) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            ,
    
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,//Keep text centered
            modifier = modifier
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = ("ArrowBack"),
                    tint = MaterialTheme.colorScheme.outline,
                )
            }
            //Spacer(modifier = modifier.weight(1f))
            Text(
                text = "Settings",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
            )
        }
        LazyColumn(
            modifier = modifier
                .align(Alignment.TopCenter)
                .padding(vertical = 70.dp)
        ) {
            items(settingsText) {text ->
                Card(
                    modifier = modifier
                        .padding(vertical = 3.dp)
                        .clickable(
                            onClick = {

                            }
                        ),
                    shape = RoundedCornerShape(20.dp),
                ) {
                    Row(
                    modifier = modifier
                        .padding(horizontal = 15.dp)
                    ) {
                        Text(
                            text = text.text,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 16.sp,
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                        )
                    }
                }
            }
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    modifier = modifier
                        .padding(vertical = 3.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .padding(horizontal = 15.dp)
                            .padding(end = 20.dp)
                    ) {
                        Text(
                            text = "Dark Theme",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 16.sp,
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                        )
                        var checked by remember {
                            mutableStateOf(true)
                        }
                        Switch(
                            checked = checked,
                            onCheckedChange = { checked = it },
                            modifier = modifier

                        )
                    }
                }
            }
        }
    }
}