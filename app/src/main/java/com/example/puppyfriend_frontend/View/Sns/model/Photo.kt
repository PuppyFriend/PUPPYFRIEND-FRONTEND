package com.example.puppyfriend_frontend.View.Sns.model

data class Photo(
    val id: Long, // 이미지 식별자 (예: MediaStore.Images.Media._ID)
    val url: String // 이미지의 URL 또는 로컬 경로
)