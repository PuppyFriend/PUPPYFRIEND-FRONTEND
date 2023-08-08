package com.example.puppyfriend_frontend.Model

data class Chat(
    var profile : String,
    var chat_id : Int,
    var senderName : String,
    var sendTime : String,
    var content : String,
    var viewType : Int
)
