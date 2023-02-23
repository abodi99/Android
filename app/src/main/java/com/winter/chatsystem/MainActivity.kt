package com.winter.chatsystem

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.winter.chatsystem.components.*
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


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    
    Scaffold(
        content = {
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
                composable("signup") {
                    SignUpScreen()
                }
                composable("profile") {
                    AccountSettingsScreen(navController)
                }
                composable("chat") {
                    //val id = it.arguments!!.getString("id")!!.toInt()
                    OneToOne(navController)
                }
            }
        },
        //bottomBar = { BottomNavBar(navController) }
    )
}