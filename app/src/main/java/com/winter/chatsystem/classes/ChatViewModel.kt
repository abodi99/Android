package com.winter.chatsystem.classes

import android.content.Context
import android.os.Message
import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.Constants.MessageNotificationKeys.TAG
import java.util.*
import kotlin.collections.HashMap




var messageCounter = 0


class ChatViewModel(private val context: Context): ViewModel() {
    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> = _messages

    private val firebaseDatabase = Firebase.database.reference



    private val sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    var messageCounter = sharedPreferences.getInt("messageCounter", 0)

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



    fun checkIfEmailExists(newEmail: String, callback: (Boolean) -> Unit) {
        val auth = FirebaseAuth.getInstance()

        auth.fetchSignInMethodsForEmail(newEmail)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val emailExists = task.result?.signInMethods
                    val result = task.result
                    if (emailExists?.isNotEmpty() == true) {
                        // Email already exists
                        callback(true)
                        println("okay")
                    } else {
                        callback(false)
                        println("Not okay")

                    }
                } else {
                    callback(false)
                }
            }
    }


    fun startNewChat(otherUserEmail: String) {
        val auth = Firebase.auth
        val currentUser = auth.currentUser
        val currentUserEmail = currentUser?.email ?: return
        val modifiedCurrentEmail = currentUserEmail.split("@")[0]
        val modifiedOtherEmail = otherUserEmail.split("@")[0]

        val chatId = "${modifiedCurrentEmail}-${modifiedOtherEmail}"
        val chatIdReversed = "${modifiedOtherEmail}-${modifiedCurrentEmail}"

        val databaseRef = FirebaseDatabase.getInstance().getReference("chats")
        databaseRef.child(chatId).get().addOnSuccessListener { chatSnapshot ->
            if (chatSnapshot.exists()) {
                // Chat already exists
                Log.d(TAG, "Chat already exists with ID: ${chatSnapshot.key}")
            } else {
                // Chat doesn't exist, check reverse order
                databaseRef.child(chatIdReversed).get().addOnSuccessListener { reverseSnapshot ->
                    if (reverseSnapshot.exists()) {
                        // Chat already exists in reverse order
                        Log.d(TAG, "Chat already exists with ID: ${reverseSnapshot.key}")
                    } else {
                        // Chat doesn't exist in either order, create new chat
                        val participants = hashMapOf(
                            modifiedCurrentEmail to true,
                            modifiedOtherEmail to true
                        )
                        val chat = Chats(chatId, 0, hashMapOf(), participants)
                        databaseRef.child(chat.chatId!!).setValue(chat)
                            .addOnSuccessListener {
                                Log.d(TAG, "New chat created with ID: ${chat.chatId}")
                            }
                            .addOnFailureListener { e ->
                                Log.e(TAG, "Error creating new chat with ID: ${chat.chatId}", e)
                            }
                    }
                }
            }
        }.addOnFailureListener { e ->
            Log.e(TAG, "Error checking chat existence with ID: $chatId", e)
        }
    }







    fun sendMessage(chatId: String, text: String, senderID: String) {
        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference("/chats/$chatId/messages").push()
        val messageMap = HashMap<String, Any>()
        val currentUser = FirebaseAuth.getInstance().currentUser

        val messageId = UUID.randomUUID().toString() // Generate a random UUID


        messageMap["messageId"] = "message-${messageCounter++}"
        messageMap["message"] = text
        messageMap["senderId"] = currentUser!!.uid
        messageMap["timestamp"] = ServerValue.TIMESTAMP
        reference.setValue(messageMap).addOnSuccessListener {
            // code to be executed on success
            println("Message sent: success")
            database.getReference("/chats/$chatId/timestamp").setValue(messageMap["timestamp"])
            database.getReference("/chats/$chatId/read").setValue(false)
            database.getReference("/chats/$chatId/sendId").setValue(currentUser.uid)



            sharedPreferences.edit().putInt("messageCounter", messageCounter).apply()

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


