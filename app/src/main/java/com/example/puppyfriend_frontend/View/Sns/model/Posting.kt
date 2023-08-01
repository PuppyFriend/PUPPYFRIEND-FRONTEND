package com.example.puppyfriend_frontend.View.Sns.model

import android.graphics.drawable.Drawable
import com.example.puppyfriend_frontend.R

data class Posting(
    val date: String,
    val image: Int,        // Drawable 타입으로 변경
    val content: String,
    val contentBackgroundColor: Int

)
