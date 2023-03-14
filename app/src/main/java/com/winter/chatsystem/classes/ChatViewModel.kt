package com.winter.chatsystem.classes

import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.Constants.MessageNotificationKeys.TAG
import java.util.*
import kotlin.collections.HashMap


class ChatViewModel: ViewModel() {
    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> = _messages

    private val firebaseDatabase = Firebase.database.reference


    fun fetchMessages() {
        val messagesRef = firebaseDatabase.child("messages")
        messagesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val messages = mutableListOf<Message>()
                snapshot.children.forEach { dataSnapshot ->  
                    val message = dataSnapshot.getValue(Message::class.java)
                    message?.let {
                        messages.add(it)
                    }
                }
                _messages.postValue(messages)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "Failed to fetch messages: ${error.message}")
            }
        })
    }

    // Define a LiveData object to show the toast message
    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    fun sendMessage(chatId: String, text: String, senderID: String) {
        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference("/chats/$chatId/messages").push()
        val messageMap = HashMap<String, Any>()
        val currentUser = FirebaseAuth.getInstance().currentUser

        messageMap["messageId"] = reference.key!!
        messageMap["message"] = text
        messageMap["senderId"] = currentUser!!.uid
        messageMap["timestamp"] = ServerValue.TIMESTAMP
        reference.setValue(messageMap).addOnSuccessListener {
            // code to be executed on success
            println("Message sent: success")
        }.addOnFailureListener {
            println("Message sent: $text")

        }


    }

    fun printMessages(chatId: String) {
        val messagesRef = firebaseDatabase.child("chats").child(chatId).child("messages")
        messagesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (messageSnapshot in snapshot.children) {
                    val messageId = messageSnapshot.key
                    val message = messageSnapshot.child("message").value as String
                    val senderId = messageSnapshot.child("senderId").value as String
                    val timestamp = messageSnapshot.child("timestamp").value as Long
                    println("MessageId: $messageId, Message: $message, SenderId: $senderId, Timestamp: $timestamp")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "Failed to fetch messages: ${error.message}")
            }
        })
    }

}


