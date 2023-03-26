package com.winter.chatsystem.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.winter.chatsystem.R

@Composable
fun BottomNavBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    var selectedItem by rememberSaveable { mutableStateOf(1) }
    val items = listOf(stringResource(id = R.string.Settings), stringResource(id = R.string.Chats), stringResource(id = R.string.Profile))

    NavigationBar() {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier
                .height(80.dp)
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = {
                        if (index == 0) {
                            Icon(
                                Icons.Filled.Settings,
                                contentDescription = item,
                                //tint = MaterialTheme.colorScheme.onPrimary
                            )
                        } else if (index == 1) {
                            Icon(
                                Icons.Filled.Home,
                                contentDescription = item,
                                //tint = MaterialTheme.colorScheme.onPrimary
                            )
                        } else {
                            Icon(
                                Icons.Filled.Person,
                                contentDescription = item,
                                //tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    },
                    label = {
                        if (selectedItem == index) {
                            Text(item, fontWeight = FontWeight.ExtraBold)
                        } else {
                            Text(item)
                        }
                    },
                    selected = selectedItem == index,
                    onClick = {
                        selectedItem = index
                        navController.navigate(item.lowercase())
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                        indicatorColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    modifier = Modifier
                        .fillMaxHeight()
                )
            }
        }
    }
}