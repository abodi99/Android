package com.winter.chatsystem.components

import android.annotation.SuppressLint
import android.icu.text.DateFormat
import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.Dp
import com.google.firebase.database.FirebaseDatabase
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
                                                val database = FirebaseDatabase.getInstance()
                                                database
                                                    .getReference("/chats/${chat.chatId}/read")
                                                    .setValue(true)
                                                print(chat.read)
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
                                                    chat.chatId!!.substringAfter("-").capitalize()
                                                                                                                                } else {
                                                    chat.chatId!!.substringBefore("-").capitalize()
                                                },
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 20.sp,
                                                color = MaterialTheme.colorScheme.onBackground,
                                                modifier = Modifier
                                            )
                                        }
                                    )




                                    Column(modifier = Modifier.weight(1f)) {
                                      //  val lastMessage = chat.messages!!.values!!.lastOrNull()
                                        val lastMessageTimestamp = chat.timestamp ?: 0L
                                        //Text(text = lastMessageText)

                                        Text(text = DateFormat.getTimeInstance().format(Date(lastMessageTimestamp)), modifier = Modifier.align(
                                            Alignment.End
                                        ).padding(6.dp),color =
                                             MaterialTheme.colorScheme.onBackground, fontSize = 11.sp
                                        )
                                    }
                                    if(chat.read == false && chat.sendId != user.uid!!){
                                        UnreadMessageCircle()
                                    }


                                }
                            }
                        }


                    }

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
                    label = { Text("Enter email") },
                    singleLine = true,

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

@Composable
fun UnreadMessageCircle(
    size: Dp = 14.dp,
    //    strokeWidth: Dp = 2.dp,
    color: Color = Color.Green,

    ) {
    Box(modifier = Modifier.size(size).fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val radius = size.toPx() / 2
            drawCircle(
                color = color,
                radius = radius,
                //  center = Offset(radius, radius),
                //  style = Stroke(width = strokeWidth.toPx())
            )
        }
    }
}