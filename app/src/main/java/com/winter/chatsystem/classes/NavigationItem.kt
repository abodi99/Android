package com.winter.chatsystem.classes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector


sealed class NavigationItem(var route: String, var icon: ImageVector, var title: String) {
    object Settings : NavigationItem("settings", Icons.Filled.Settings, "Settings")
    object Home : NavigationItem("home", Icons.Filled.Home, "Home")
    object ProfileSettings : NavigationItem("profile", Icons.Filled.Person, "Profile")
}