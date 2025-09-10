package com.example.chatting_app.presentation.homescreen




data class ChatBox(
    val name:String? = null,
    val phoneNumber: String?=null,
    val image:String? = null,
    val userId: String?= null,
    val time:String?= null,
    val message:String?= null,
    val profileImage: String?= null

){
    constructor(): this(null,null,null,null,null,null,null)
}