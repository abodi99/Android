package com.winter.chatsystem.logic

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn

data class ChatMessage(
    val messageId: String = "",
    val oneMessage: String = "",
    val senderId: String = "",
    val timestamp: Long = 0
)

fun getChatMessages(chatId: String): Flow<List<ChatMessage>> {
    val database = Firebase.database.reference
    val messagesRef = database.child("chats").child(chatId).child("messages")



    return callbackFlow {
        val eventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val messages = mutableListOf<ChatMessage>()
                for (messageSnapshot in snapshot.children) {
                    val messageId = messageSnapshot.key as String
                    val message = messageSnapshot.child("message").value as String
                    val senderId = messageSnapshot.child("senderId").value as String
                    val timestamp = messageSnapshot.child("timestamp").value as Long
                    val chatMessage = ChatMessage(messageId, message, senderId, timestamp)
                    messages.add(chatMessage)

                }
                this@callbackFlow.trySend(messages).isSuccess

            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        messagesRef.addValueEventListener(eventListener)

        awaitClose {
            messagesRef.removeEventListener(eventListener)
        }
    }.flowOn(Dispatchers.IO)
}




