package com.winter.chatsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.winter.chatsystem.components.OneToOne
import com.winter.chatsystem.components.AccountSettingsScreen
import com.winter.chatsystem.components.LoginScreen
import com.winter.chatsystem.components.SettingsScreen
import com.winter.chatsystem.components.SignUpScreen
import com.winter.chatsystem.ui.theme.ChatSystemTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatSystemTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppScreen()
                }
            }
        }
    }
}

data class SettingsText(val text: String)
var settingsText = listOf(
    SettingsText("Edit Profile"),
    SettingsText("Change Password"),
)


@Composable
fun AppScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "chat"
    ) {
        composable("settings") {
            SettingsScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("profile") {
            AccountSettingsScreen(navController)
        }
        composable("chat") {
            //val id = it.arguments!!.getString("id")!!.toInt()
            OneToOne(navController)
        }
    }
}