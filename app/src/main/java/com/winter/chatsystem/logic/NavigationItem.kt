package com.winter.chatsystem.logic

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.winter.chatsystem.R


sealed class NavigationItem(var route: String, var icon: ImageVector, var titleResId: Int) {
    object Settings : NavigationItem("settings", Icons.Filled.Settings, R.string.Settings)
    object Home : NavigationItem("home", Icons.Filled.Home, R.string.Home)
    object ProfileSettings : NavigationItem("profile", Icons.Filled.Person, R.string.Profile)

    fun getTitle(context: Context): String {
        return context.getString(titleResId)
    }
}