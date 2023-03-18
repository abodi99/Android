package com.winter.chatsystem.classes

data class Chats(
    val participants: HashMap<String, Boolean> = hashMapOf(),
    val messages: HashMap<String, ChatMessage> = hashMapOf(),
    var chatId: String = ""
)

