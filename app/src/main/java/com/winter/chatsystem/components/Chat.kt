package com.winter.chatsystem.components

import android.annotation.SuppressLint
import android.icu.text.DateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.winter.chatsystem.classes.ChatViewModel
import com.winter.chatsystem.classes.getChats

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.util.*


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val chats by getChats().collectAsState(emptyList())


    val context = LocalContext.current
    val chatViewModel = ChatViewModel(context)
    val auth = Firebase.auth
    val user = auth.currentUser
    val currentUserEmail = user?.email
    val displayName = user?.displayName

    var newChatEmail by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        content = {

            LazyColumn(
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp,
                        top = 75.dp,
                        bottom = 56.dp

                    ),
                content = {
                    items(chats) { chat ->
                        val chatIds = chat.chatId!!.split("-")

                        if(chatIds[0]!! == currentUserEmail!!.split("@")[0] || chatIds[1]!! == currentUserEmail!!.split("@")[0] ){
                            Column(
                                modifier = Modifier
                                    .padding(0.dp)
                                    .background(color = MaterialTheme.colorScheme.background),
                               // shape = RoundedCornerShape(6.dp),

                                ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(6.dp)

                                        // .border(1.dp, Color.Gray)
                                        .clickable(
                                            onClick = {
                                                navController.navigate(chat.chatId!!)
                                            }
                                        )
                                ){
                                    Icon(
                                        Icons.Filled.Person,
                                        contentDescription = "user",
                                        modifier = Modifier
                                            .size(25.dp),
                                        tint = MaterialTheme.colorScheme.onBackground,

                                        )
                                    Column(
                                        modifier = Modifier
                                            .padding(16.dp),

                                        content = {
                                            Text(
                                                text = if(chat.chatId!!.substringAfter("-") != currentUserEmail!!.split("@")[0]){
                                                    chat.chatId!!.substringAfter("-")
                                                                                                                                } else {
                                                    chat.chatId!!.substringBefore("-")
                                                },
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 20.sp,
                                                color = MaterialTheme.colorScheme.onBackground,
                                                modifier = Modifier
                                            )
                                            /*Text(
                                                text = user?.email.toString(),
                                                fontWeight = FontWeight.Light,
                                                fontSize = 14.sp,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                modifier = Modifier
                                            )*/
                                        }
                                    )
                                    Column(modifier = Modifier.weight(1f)) {
                                        val lastMessage = chat.messages?.values?.lastOrNull()
                                        val lastMessageText = lastMessage?.oneMessage ?: "No Messages yet"
                                        val lastMessageTimestamp = chat.timestamp ?: 0L
                                        //Text(text = lastMessageText)
                                        Text(text = DateFormat.getTimeInstance().format(Date(lastMessageTimestamp)), modifier = Modifier.align(
                                            Alignment.End
                                        ),color = MaterialTheme.colorScheme.onBackground, fontSize = 11.sp
                                        )
                                    }

                                }
                            }
                        }


                    }

                    /*item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("talal3-talal10")
                                    }
                                )
                        ) {
                            Icon(
                                Icons.Filled.Person,
                                contentDescription = "user",
                                modifier = Modifier
                                    .padding(end = 12.dp)
                                    .size(40.dp)
                            )
                            Column(
                                content = {
                                    Text(
                                        text = user?.displayName.toString(),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier
                                    )
                                    Text(
                                        text = user?.email.toString(),
                                        fontWeight = FontWeight.Light,
                                        fontSize = 14.sp,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier
                                    )
                                }
                            )
                        }
                    }

                     */

                    item {
                        OutlinedButton(
                            onClick = { showDialog = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp)
                        ) {
                            Text("Start a new conversation")
                        }
                    }
                }
            )
        },



            //bottomBar = { BottomNavBar(navController) },
        )



    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Start a new conversation") },
            text = {
                OutlinedTextField(
                    value = newChatEmail,
                    onValueChange = { newChatEmail = it },
                    label = { Text("Enter email") }
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        // TODO: Create new chat
                        showDialog = false
                        chatViewModel.checkIfEmailExists(newChatEmail) { exists ->
                            if (exists){
                                chatViewModel.startNewChat(newChatEmail)
                            } else {

                            }
                        }
                        //newChatEmail=""


                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDialog = false }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}