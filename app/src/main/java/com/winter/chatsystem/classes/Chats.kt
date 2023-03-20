package com.winter.chatsystem.classes

data class Chats(
    var chatId: String? = null,
    var timestamp: Long = 0L,
    val messages: Map<String, ChatMessage>? = null,
    val participants: Map<String, Boolean>? = null

)

