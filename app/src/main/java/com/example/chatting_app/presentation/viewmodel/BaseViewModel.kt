package com.example.chatting_app.presentation.viewmodel


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.chatting_app.models.Message
import com.example.chatting_app.presentation.homescreen.ChatBox
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import android.util.Base64
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream
import kotlin.io.encoding.ExperimentalEncodingApi

class BaseViewModel: ViewModel() {
    fun SearchUserByPhoneNumber(phoneNumber: String, callback: (ChatBox?) -> Unit) {

        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            Log.e("BaseViewModel", "User is not authenticated")
            callback(null)
            return

        }
        val databaseReference = FirebaseDatabase.getInstance().getReference("user")
        databaseReference.orderByChild("phoneNumber").equalTo(phoneNumber)
            .addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val user = snapshot.children.first().getValue(ChatBox::class.java)
                        callback(user)
                    } else {
                        callback(null)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(
                        "BaseViewModel",
                        "Error fetching User: ${error.message},Details:${error.details}"
                    )
                    callback(null)
                }
            }
            )
    }

    fun getChatForUser(userId: String, callback: (List<ChatBox>)-> Unit){
        val chatref= FirebaseDatabase.getInstance().getReference("users/$userId/chats")
        chatref.orderByChild("userId").equalTo(userId).addListenerForSingleValueEvent(object:
            ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val chatList=mutableListOf<ChatBox>()
                    for (childSnapshot in snapshot.children){
                        val chat =childSnapshot.getValue(ChatBox::class.java)

                        if(chat!=null){
                            chatList.add(chat)
                        }
                    }
                    callback(chatList)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("BaseViewModel", "Error fetching User Chats: ${error.message}")
                    callback(emptyList())
                }
            }
        )
    }
    private val _chatList= MutableStateFlow<List<ChatBox>>(emptyList())
    val chatList = _chatList.asStateFlow()

    init {
       loadChatData()
    }
    private fun loadChatData(){
        val currentuserId = FirebaseAuth.getInstance().currentUser?.uid

        if(currentuserId!=null){
            val chatRef = FirebaseDatabase.getInstance().getReference("chats")
            chatRef.orderByChild("userId").equalTo(currentuserId).addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val chatList=mutableListOf<ChatBox>()
                    for(childSnapshot in snapshot.children){
                        val chat = childSnapshot.getValue(ChatBox::class.java)
                        if(chat!= null){
                            chatList.add(chat)
                        }
                    }
                    _chatList.value=chatList
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("BaseViewModel", "Error user chats:${error.message}")
                }

            })

        }
    }
    fun addChat(newChat: ChatBox){
        val currentUserId= FirebaseAuth.getInstance().currentUser?.uid
        if(currentUserId !=null){
            val newChatRef = FirebaseDatabase.getInstance().getReference("chats").push()
            val chatWithUser = newChat.copy(currentUserId)
            newChatRef.setValue(chatWithUser).addOnSuccessListener {
                Log.d("BaseViewModel", "chat added successfully to firebase")
            }.addOnFailureListener {exception->
                Log.e("BaseViewModel","Failed to add chat:${exception.message}")
            }
        }else{
            Log.e("BaseViewModel","No user is authenticated")
        }
    }
    private val databaseReference= FirebaseDatabase.getInstance().reference
    fun sendMessage(senderPhoneNumber: String, recieverPhoneNumber: String, messageText: String){
        val messageId= databaseReference.push().key?:return

         val message= Message(
             senderPhoneNumber=senderPhoneNumber,
             message= messageText,
             timeStamp = System.currentTimeMillis()
         )

        databaseReference.child("messages")
            .child(senderPhoneNumber)
            .child(recieverPhoneNumber)
            .child(messageId)
            .setValue(message)

        databaseReference.child("messages")
            .child(recieverPhoneNumber)
            .child(senderPhoneNumber)
            .child(messageId)
            .setValue(message)
    }
    fun getMesage(
        senderPhoneNumber: String,
        recieverPhoneNumber: String,
        onNewMessage: (Message)->Unit
    ){
        val messageRef = databaseReference.child("message").child(senderPhoneNumber).child(recieverPhoneNumber)
        messageRef.addChildEventListener(object: ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val message= snapshot.getValue(Message::class.java)
                if(message!=null){
                    onNewMessage(message)
                }
            }

            override fun onChildChanged(
                snapshot: DataSnapshot,
                previousChildName: String?
            ) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(
                snapshot: DataSnapshot,
                previousChildName: String?
            ) {

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
    fun fetchLastMessageForChat(
        senderPhoneNumber: String,
        recieverPhoneNumber: String,
        onLastMessageFetch:(String, String)->Unit
    ){
        val chatRef= FirebaseDatabase.getInstance().reference.child("message").child(senderPhoneNumber).child(recieverPhoneNumber)

        chatRef.orderByChild("timestamp").limitToLast(1).addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    val lastMessage= snapshot.children.firstOrNull()?.child("message")?.value as? String
                    val timestamp= snapshot.children.firstOrNull()?.child("timestamp")?.value as? String

                    onLastMessageFetch(lastMessage?:"No Message", timestamp?: "--:--" )
                }
                else{
                    onLastMessageFetch("No mesage", "--:--")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                onLastMessageFetch("No mesage", "--:--")
            }
        })

    }
    fun loadChatList(
        currentUserPhoneNumber: String,
        onChatListLoaded:(List<ChatBox>)->Unit
    ){
        val chatList= mutableListOf<ChatBox>()
        val chatRef= FirebaseDatabase.getInstance().reference.child("chats").child(currentUserPhoneNumber)
        chatRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    snapshot.children.forEach { child->
                        val phoneNumber= child.key?: return@forEach
                        val name= child.child("name").value as? String?:"Unknown"
                        val image = child.child("image").value as? String

                        val profileImageBitmap= image?.let {decodeBase64toBitmap(it)}

                        fetchLastMessageForChat(currentUserPhoneNumber,phoneNumber){lastMessage,time->
                            chatList.add(
                                ChatBox(
                                    name=name,
                                    image= profileImageBitmap.toString(),
                                    message = lastMessage,
                                    time = time
                                )
                            )
                            if(chatList.size==snapshot.childrenCount.toInt()){
                                onChatListLoaded(chatList)
                            }
                        }
                    }
                }
                else{
                    onChatListLoaded(emptyList())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                onChatListLoaded(emptyList())
            }

        })
    }
//    @OptIn(ExperimentalEncodingApi::class)
    private fun decodeBase64toBitmap(base64Image: String):Bitmap?{

        return try {
            val decodedByte= Base64.decode(base64Image,android.util.Base64.DEFAULT)
            BitmapFactory.decodeByteArray(decodedByte,0,decodedByte.size)
        }
        catch (e: IOException){
            null
        }
    }
    fun base64ToBitmap(base64String: String): Bitmap?{
        return try {
            val decodedByte= Base64.decode(base64String,android.util.Base64.DEFAULT)
            val inputStream: InputStream= ByteArrayInputStream(decodedByte)
            BitmapFactory.decodeStream(inputStream)
        }
        catch (e: IOException){
            null
        }
    }
}