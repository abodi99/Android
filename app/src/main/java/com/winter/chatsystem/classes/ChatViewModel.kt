package com.winter.chatsystem.classes

import android.os.Message
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.Constants.MessageNotificationKeys.TAG

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

    fun sendMessage(chatId: String, text: String, currentUser: FirebaseUser) {
        val reference = FirebaseDatabase.getInstance().getReference("/chats/$chatId/messages").push()
        val messageMap = HashMap<String, Any>()
        messageMap["messageId"] = reference.key!!
        messageMap["message"] = text
        messageMap["senderId"] = currentUser.uid
        messageMap["timestamp"] = ServerValue.TIMESTAMP
        reference.setValue(messageMap)
    }
}