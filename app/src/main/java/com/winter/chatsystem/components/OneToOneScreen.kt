package com.winter.chatsystem.components

import android.annotation.SuppressLint

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth

import com.winter.chatsystem.classes.ChatViewModel
import com.winter.chatsystem.classes.getChatMessages
import java.util.*


@Composable
fun ChatScreen(chatId: String) {
    val messages by getChatMessages(chatId).collectAsState(emptyList())

    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(top = 60.dp, bottom = 156.dp)) {
        items(messages) { message ->

            if (message.senderId != FirebaseAuth.getInstance().currentUser!!.uid.toString()) {
                println(message.senderId)
                println(FirebaseAuth.getInstance().currentUser!!.uid)
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Surface(
                        modifier = Modifier
                            .padding(6.dp)
                            .width(270.dp)
                            .wrapContentSize(Alignment.CenterStart),
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        shape = RoundedCornerShape(
                            topEnd = 10.dp,
                            bottomEnd = 50.dp,
                            bottomStart = 15.dp,
                            topStart = 15.dp
                        ),
                        shadowElevation = 0.dp
                    ) {
                        Text(
                            text = message.oneMeassage,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                                //  .height(35.dp)
                                .wrapContentSize(),
                            textAlign = TextAlign.Start
                        )
                    }
                }

            } else {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Surface(
                        modifier = Modifier
                            .padding(6.dp)
                            .width(270.dp)
                            .wrapContentSize(Alignment.CenterEnd),

                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(
                            topEnd = 15.dp,
                            bottomEnd = 15.dp,
                            bottomStart = 50.dp,
                            topStart = 10.dp
                        ),
                        shadowElevation = 0.dp
                    ) {
                        Text(
                            text = message.oneMeassage,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(7.dp)
                                //   .height(35.dp)
                                .wrapContentSize(),
                            textAlign = TextAlign.Start
                        )
                    }
                }

            }
        }
    }
}
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun OneToOne(
        navController: NavHostController,
        id: Int
    ) {
        var textFieldValue by remember { mutableStateOf("") }
        val auth = FirebaseAuth.getInstance()
        val context = LocalContext.current
        val chatViewModel = ChatViewModel(context)

        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Rounded.ArrowBack,
                        contentDescription = "back",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .clickable(
                                onClick = {
                                    navController.popBackStack()
                                }
                            )
                    )
                    Icon(
                        Icons.Rounded.AccountCircle,
                        contentDescription = "back",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .size(45.dp)
                            .padding(6.dp)
                            .clip(CircleShape)
                            .border(
                                width = 4.dp,
                                MaterialTheme.colorScheme.onSurface,
                                shape = CircleShape
                            )
                    )
                    Text(
                        text = "Talal",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 27.sp,
                        modifier = Modifier
                    )
                }
                Divider(
                    color = MaterialTheme.colorScheme.primary,
                    thickness = 2.dp,
                    modifier = Modifier
                        .padding(vertical = 59.dp)
                )
            },
            content = {

                ChatScreen("chatId1")

                /* LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 60.dp, bottom = 156.dp),
            ){
                items(20){ item ->
                    if (item % 2 == 0) {
                        Box(modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterStart) {
                            Surface(
                                modifier = Modifier
                                    .padding(6.dp)
                                    .width(270.dp)
                                    .wrapContentSize(Alignment.CenterStart),
                                color = MaterialTheme.colorScheme.tertiaryContainer,
                                shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 50.dp, bottomStart = 15.dp, topStart = 15.dp),
                                shadowElevation = 0.dp
                            ) {
                                Text(text = "Hello, whats up?",
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                                    fontSize = 18.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(7.dp)
                                        //  .height(35.dp)
                                        .wrapContentSize(),
                                    textAlign = TextAlign.Justify
                                )
                            }
                        }
                    } else {
                        Box(modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd) {
                            Surface(
                                modifier = Modifier
                                    .padding(6.dp)
                                    .width(270.dp)
                                    .wrapContentSize(Alignment.CenterEnd),

                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = RoundedCornerShape(topEnd = 15.dp, bottomEnd = 15.dp, bottomStart = 50.dp, topStart = 10.dp),
                                shadowElevation = 0.dp
                            ) {
                                Text(text = "Hi, how you doing?",
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    fontSize = 18.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(7.dp)
                                        //   .height(35.dp)
                                        .wrapContentSize(),
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                    }
                }
            }

            */

                //Spacer(modifier = Modifier.weight(1f))
            },
            bottomBar = {
                Column(
                    modifier = Modifier
                        .padding(bottom = 80.dp)
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(69.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        OutlinedTextField(
                            value = textFieldValue,
                            onValueChange = { textFieldValue = it },
                            // label = { Text(text = "Write your message", color = MaterialTheme.colorScheme.onPrimary)},
                            placeholder = {
                                Text(
                                    text = "Write your message",
                                    //color = MaterialTheme.colorScheme.onTertiary
                                )
                            },
                            modifier = Modifier
                                .width(420.dp)
                                .padding(bottom = 4.5.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(30.dp),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Face,
                                    contentDescription = "Msg",
                                    //tint = MaterialTheme.colorScheme.onPrimary
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Send,
                                    contentDescription = "Send",
                                    modifier = Modifier
                                        .clickable(
                                            onClick = {
                                                val chatId = "chatId1"
                                                val message = textFieldValue
                                                val currentUser =
                                                    FirebaseAuth.getInstance().currentUser
                                                if (!message.isBlank()) {
                                                    chatViewModel.sendMessage(
                                                        "chatId1",
                                                        message,
                                                        currentUser!!.uid.toString()
                                                    )
                                                    textFieldValue = ""
                                                }
                                                // chatViewModel.printMessages(chatId)


                                            }
                                            //tint = MaterialTheme.colorScheme.onPrimary,
                                        ))
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                //focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                //focusedTrailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                focusedPlaceholderColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        )
                        Icon(
                            Icons.Default.AddCircle, contentDescription = "Mic",
                            modifier = Modifier
                                .padding(start = 6.dp, end = 6.dp)
                                .size(35.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    //BottomNavBar(navController)
                }
            }
        )
    }
