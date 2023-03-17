package com.winter.chatsystem.classes

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

data class createChat(
    val participants: Map<String, Boolean> = emptyMap()

)

/**
 * Creates a new chat with the specified users as participants.
 * Returns the ID of the newly created chat.
 */
suspend fun createNewChat(user1Id: String, user2Id: String): String {
    val chat = createChat(mapOf(user1Id to true, user2Id to true))
    val database = Firebase.database.reference
    val chatRef = database.child("chats").push()
    chatRef.setValue(chat).await()
    return chatRef.key ?: throw IllegalStateException("Chat ID is null")
}