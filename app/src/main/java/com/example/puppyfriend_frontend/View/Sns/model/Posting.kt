package com.example.puppyfriend_frontend.View.Sns.model

data class Posting(
    val date: String = "weer",
    val image: Int,
    val content: String = "WEr",
    val contentBackgroundColor: Int = 1,
    var isChecked: Boolean = false,
    var isDeleted: Boolean = false
)