package com.winter.chatsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.winter.chatsystem.settingsText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
    
    ) {
        Row(
            verticalAlignment =Alignment.CenterVertically//Keep text centered
        ) {
            IconButton(
                ///modifier = modifier,
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = ("ArrowBack"),
                    tint = MaterialTheme.colorScheme.outline,
                )
            }
            Spacer(modifier = modifier.weight(1f))
            Text(
                text = "Settings",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineSmall,
            )
            Spacer(modifier = modifier.weight(2f))
        }
        LazyColumn(
            modifier = modifier
                .align(Alignment.TopCenter)
                .padding(vertical = 70.dp)
        ) {
            items(settingsText) {text ->
                Card(
                    shape = RoundedCornerShape(20.dp),
                    modifier = modifier
                        .padding(vertical = 3.dp)
                        .size(300.dp, 40.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(
                            onClick = { /*TODO*/ },
                            modifier = modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.primaryContainer)
                        ) {
                            Text(
                                text = text.text,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                fontSize = 14.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}