package com.example.puppyfriend_frontend.Model

import com.google.gson.annotations.SerializedName

data class UserJoinDto(
    @SerializedName(value = "birth") val birth : String, // 2000-06-17
    @SerializedName(value = "email") val email : String,
    @SerializedName(value = "gender") val gender : Boolean,
    @SerializedName(value = "location") val location : String,
    @SerializedName(value = "marketing") val marketing : Boolean,
    @SerializedName(value = "name") val name : String,
    @SerializedName(value = "nickname") val nickname : String,
    @SerializedName(value = "password") val password : String,
    @SerializedName(value = "uid") val uid : String
)
