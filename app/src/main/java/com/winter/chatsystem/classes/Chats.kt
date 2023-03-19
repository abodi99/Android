package com.winter.chatsystem.classes

data class Chats(
    var chatId: String? = null,
    val messages: Map<String, ChatMessage>? = null,
    val participants: Map<String, Boolean>? = null
)

