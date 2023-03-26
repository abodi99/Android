package com.winter.chatsystem.logic

data class Chats(
    var chatId: String? = null,
    var timestamp: Long = 0L,
    val messages: Map<String, ChatMessage>? = null,
    val participants: Map<String, Boolean>? = null,
    var read: Boolean = false,
    var sendId: String? = null

)

