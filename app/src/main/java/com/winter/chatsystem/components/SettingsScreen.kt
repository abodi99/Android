package com.winter.chatsystem.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

import androidx.compose.material3.Switch
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.winter.chatsystem.MainActivity
import com.winter.chatsystem.R

import com.winter.chatsystem.darkMode
import com.yariksoffice.lingver.Lingver
import java.util.*


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    Scaffold(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                ) {
                LazyColumn(
                    modifier = modifier
                        .align(Alignment.TopCenter)
                        .padding(vertical = 70.dp)
                ) {
                    item {
                        Card(
                            shape = RoundedCornerShape(20.dp),
                            modifier = modifier
                                .padding(vertical = 3.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = modifier
                                    .padding(horizontal = 15.dp)
                                    .padding(end = 20.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.Profile),
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    fontSize = 16.sp,
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 10.dp)
                                        .clickable {
                                            navController.navigate("profile")
                                        },
                                )
                            }
                        }
                    }
                    item {

                        Card(
                            shape = RoundedCornerShape(20.dp),
                            modifier = modifier
                                .padding(vertical = 3.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = modifier
                                    .padding(horizontal = 15.dp)
                                    .padding(end = 20.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.English),
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    fontSize = 16.sp,
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 10.dp)
                                        .clickable {
                                            Lingver.getInstance().setLocale(MainActivity.instance, "en")
                                            MainActivity.instance.recreate()

                                        },


                                )




                            }
                        }

                    }
                    item {

                        Card(
                            shape = RoundedCornerShape(20.dp),
                            modifier = modifier
                                .padding(vertical = 3.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = modifier
                                    .padding(horizontal = 15.dp)
                                    .padding(end = 20.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.Swedish),
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    fontSize = 16.sp,
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 10.dp)
                                        .clickable {
                                            Lingver.getInstance().setLocale(MainActivity.instance, "sv")
                                            MainActivity.instance.recreate()

                                        },
                                    //MainActivity.instance.recreate()


                                )




                            }
                        }

                    }


                    item {
                        Card(
                            shape = RoundedCornerShape(20.dp),
                            modifier = modifier
                                .padding(vertical = 3.dp),
                            colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = modifier
                                    .padding(horizontal = 15.dp)
                                    .padding(end = 20.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.DarkTheme),
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    fontSize = 16.sp,
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 10.dp)
                                )
                                val isChecked = remember { mutableStateOf(false) }
                                Switch(
                                    checked = isChecked.value,
                                    onCheckedChange = {
                                        isChecked.value = it
                                        darkMode = !darkMode


                                    }

                                )

                            }
                        }
                    }
                }
            }
        },
        //bottomBar = { BottomNavBar(navController) }
    )

}