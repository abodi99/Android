package com.winter.chatsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    Box {
        Column(
            modifier = modifier
                .align(Alignment.Center)
        ) {
            TextButton(
                modifier = modifier
                    .background(MaterialTheme.colorScheme.primary),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = modifier
                )
            }
        }
    }
}