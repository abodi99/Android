package com.winter.chatsystem

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.insets.statusBarsPadding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.winter.chatsystem.classes.NavigationItem
import com.winter.chatsystem.components.*
import com.winter.chatsystem.data.ChatScreen
import com.winter.chatsystem.ui.theme.BottomBarAnimationTheme
import com.winter.chatsystem.ui.theme.ChatSystemTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppScreen() {

    // State of bottomBar, set state to false, if current page route is ""
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    // State of topBar, set state to false, if current page route is ""
    val topBarState = rememberSaveable { (mutableStateOf(true)) }

    ChatSystemTheme {
        val navController = rememberNavController()

        // Subscribe to navBackStackEntry, required to get current route
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        // Control TopBar and BottomBar
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

        // IMPORTANT, Scaffold from Accompanist, initialized in build.gradle.
        // We use Scaffold from Accompanist, because we need full control of paddings, for example
        // in default Scaffold from Compose we can't disable padding for content from top if we
        // have TopAppBar. In our case it's required because we have animation for TopAppBar,
        // content should be under TopAppBar and we manually control padding for each pages.
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
                    startDestination = "signup",
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
                    composable("chat/1") {
                        // show BottomBar and TopBar
                        LaunchedEffect(Unit) {
                            bottomBarState.value = true
                            topBarState.value = false
                        }
                        OneToOne(
                            navController = navController, 1
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

@ExperimentalAnimationApi
@Composable
fun BottomBar(navController: NavController, bottomBarState: MutableState<Boolean>) {
    val items = listOf(
        NavigationItem.Settings,
        NavigationItem.Home,
        NavigationItem.ProfileSettings
    )

    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            NavigationBar(
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                item.icon,
                                contentDescription = item.title
                            )
                        },
                        label = { Text(text = item.title) },
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer

                        )
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalAnimationApi
@Composable
fun TopBar(navController: NavController, topBarState: MutableState<Boolean>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val title: String = when (navBackStackEntry?.destination?.route ?: "home") {
        "home" -> "Home"
        "profile" -> "Profile"
        "settings" -> "Settings"
        "chat/1" -> "Chat"
        else -> "Home"
    }

    AnimatedVisibility(
        visible = topBarState.value,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it }),
        content = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = title,
                        fontSize = 27.sp,
                        textAlign = TextAlign.Center,
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    scrolledContainerColor = MaterialTheme.colorScheme.surfaceTint
                )
            )
            Divider(
                color = MaterialTheme.colorScheme.primary,
                thickness = 2.dp,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 60.dp)
            )
        }
    )
}