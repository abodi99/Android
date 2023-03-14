/*package com.winter.chatsystem.components;

public interface storage {
}

    @Composable
    fun ChatMessageItem(chatMessage: ChatMessage) {
        val isSentByUser = chatMessage.senderId == Firebase.auth.currentUser?.uid

        Column(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                horizontalAlignment = if (isSentByUser) {
            Alignment.End
        } else {
            Alignment.Start
        }
    ) {
            Text(
                    text = chatMessage.oneMeassage,
                    color = Color.White,
                    textAlign = if (isSentByUser) {
                TextAlign.End
            } else {
                TextAlign.Start
            },
            modifier = Modifier
                    .background(
            if (isSentByUser) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.secondary
            },
            shape = if (isSentByUser) {
                RoundedCornerShape(
                        topStart = 8.dp,
                        topEnd = 0.dp,
                        bottomStart = 8.dp,
                        bottomEnd = 8.dp
                )
            } else {
                RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 8.dp,
                        bottomStart = 8.dp,
                        bottomEnd = 8.dp
                )
            }
                )
                .padding(8.dp)
        )
            Text(
                    text = chatMessage.timestamp.toString(),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    fontSize = 12.sp,
                    modifier = Modifier
                            .padding(top = 4.dp)
            )
        }
    }

    @Composable
    fun ChatScreen(chatId: String) {
        val chatMessages by getChatMessages(chatId).collectAsState(emptyList())

        LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
                reverseLayout = true
        ) {
            items(chatMessages) { chatMessage ->
                    ChatMessageItem(chatMessage = chatMessage)
            }
        }
    }
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun OneToOne(navController: NavHostController, id: Int) {
        val chatMessages = remember { mutableStateListOf<ChatMessage>() }


        // Realtime Database reference to retrieve chat messages
        val dbRef = Firebase.database.reference.child("chats/chatId$id/messages")

        // Add a listener to retrieve the chat messages from the database
        LaunchedEffect(key1 = dbRef) {
            dbRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    chatMessages.clear()
                    snapshot.children.forEach { messageSnapshot ->
                            val chatMessage = messageSnapshot.getValue(ChatMessage::class.java)
                        chatMessage?.let { chatMessages.add(it) }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }

        ChatScreen(chatId = "chatId1")
    }


 */