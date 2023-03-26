package com.winter.chatsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.winter.chatsystem.logic.NavigationItem
import com.winter.chatsystem.components.*
import com.winter.chatsystem.components.ChatScreen
import com.winter.chatsystem.logic.darkMode
import com.winter.chatsystem.ui.theme.ChatSystemTheme



class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    companion object {
        lateinit var instance: MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this

        auth = FirebaseAuth.getInstance()

        FirebaseApp.initializeApp(this)

        darkMode = com.winter.chatsystem.logic.getTheme(this)
        setContent {
            ChatSystemTheme(darkMode) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppScreen() {

    val auth = FirebaseAuth.getInstance()

    var currentUser = auth.currentUser

    // State of bottomBar, set state to false, if current page route is ""
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    // State of topBar, set state to false, if current page route is ""
    val topBarState = rememberSaveable { (mutableStateOf(true)) }

    ChatSystemTheme(darkMode) {
        val navController = rememberNavController()

        // Subscribe to navBackStackEntry, required to get current route
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        // Control TopBar and BottomBar
        if (currentUser != null) {
            when (navBackStackEntry?.destination?.route) {
                "home" -> {
                    // Show BottomBar and TopBar
                    bottomBarState.value = true
                    topBarState.value = true
                }
                "settings" -> {
                    // Show BottomBar and TopBar
                    bottomBarState.value = true
                    topBarState.value = true
                }
                "profile" -> {
                    // Show BottomBar and TopBar
                    bottomBarState.value = true
                    topBarState.value = true
                }
                "chat/${currentUser.uid}" -> {
                    // Show BottomBar and TopBar
                    bottomBarState.value = true
                    topBarState.value = true
                }
                "chat/1" -> {
                    // Show BottomBar and TopBar
                    bottomBarState.value = true
                    topBarState.value = true
                }
                "signup" -> {
                    // Hide BottomBar and TopBar
                    bottomBarState.value = false
                    topBarState.value = false
                }
                "login" -> {
                    // Hide BottomBar and TopBar
                    bottomBarState.value = false
                    topBarState.value = false
                }
            }
        }


        com.google.accompanist.insets.ui.Scaffold(
            bottomBar = {
                BottomBar(
                    navController = navController,
                    bottomBarState = bottomBarState
                )
            },
            topBar = {
                TopBar(
                    navController = navController,
                    topBarState = topBarState
                )
            },
            content = {
                NavHost(

                    navController = navController,
                    startDestination = if (auth.currentUser != null) "home" else "signup",

                    //     startDestination = "signup",
                ) {
                    composable(NavigationItem.Home.route) {
                        // show BottomBar and TopBar
                        LaunchedEffect(Unit) {
                            bottomBarState.value = true
                            topBarState.value = true
                        }
                        ChatScreen(
                            navController = navController,
                        )
                    }
                    composable(NavigationItem.ProfileSettings.route) {
                        // show BottomBar and TopBar
                        LaunchedEffect(Unit) {
                            bottomBarState.value = true
                            topBarState.value = true
                        }
                        AccountSettingsScreen(
                            navController = navController
                        )
                    }
                    composable(NavigationItem.Settings.route) {
                        // show BottomBar and TopBar
                        LaunchedEffect(Unit) {
                            bottomBarState.value = true
                            topBarState.value = true
                        }
                        SettingsScreen(
                            navController = navController,
                        )
                    }

                    composable("{chatId}") { backStackEntry ->
                        val arguments = requireNotNull(backStackEntry.arguments)
                        val chatId = arguments.getString("chatId", "")

                        // show BottomBar and TopBar
                        LaunchedEffect(Unit) {
                            bottomBarState.value = true
                            topBarState.value = false
                        }
                        OneToOne(
                            navController = navController, chatId
                        )
                    }

                    composable("login") {
                        // show BottomBar and TopBar
                        LaunchedEffect(Unit) {
                            bottomBarState.value = false
                            topBarState.value = false
                        }
                        LoginScreen(
                            navController = navController,
                        )
                    }
                    composable("signup") {
                        // show BottomBar and TopBar
                        LaunchedEffect(Unit) {
                            bottomBarState.value = false
                            topBarState.value = false
                        }
                        SignUpScreen(
                            navController = navController,
                        )
                    }
                }
            }
        )
    }
}

