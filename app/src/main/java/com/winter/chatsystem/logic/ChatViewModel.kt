package com.winter.chatsystem.logic

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.Constants.MessageNotificationKeys.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlin.collections.HashMap




class ChatViewModel(private val context: Context) : ViewModel() {


    private val sharedPreferences =
        context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    var messageCounter = sharedPreferences.getInt("messageCounter", 0)


    // Define a LiveData object to show the toast message


    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> = _loading

    fun getChats(): Flow<List<Chats>> {
        val database = Firebase.database.reference
        val chatsRef = database.child("chats")
        _loading.value = true


        return callbackFlow {
            val eventListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val chats = mutableListOf<Chats>()
                    for (chatSnapshot in snapshot.children) {
                        val chat = chatSnapshot.getValue(Chats::class.java)
                        chat?.let {
                            it.chatId = chatSnapshot.key ?: ""

                            // get the latest message in the chat
                            val latestMessage = it.messages?.values?.maxByOrNull { message ->
                                message.timestamp
                            }

                            // set the timestamp of the latest message as the chat timestamp
                            it.timestamp = latestMessage?.timestamp ?: 0

                            chats.add(it)
                        }
                    }

                    // sort the chats based on the timestamp of the latest message in descending order
                    val sortedChats = chats.sortedByDescending { chat ->
                        chat.timestamp
                    }

                    this@callbackFlow.trySend(sortedChats).isSuccess
                    _loading.value = false
                }

                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            }

            chatsRef.addValueEventListener(eventListener)

            awaitClose {
                chatsRef.removeEventListener(eventListener)
            }
        }.flowOn(Dispatchers.IO)
    }


    fun checkIfEmailExists(newEmail: String, callback: (Boolean) -> Unit) {
        val auth = FirebaseAuth.getInstance()

        auth.fetchSignInMethodsForEmail(newEmail)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val emailExists = task.result?.signInMethods
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


}


