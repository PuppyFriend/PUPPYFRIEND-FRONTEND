package com.example.puppyfriend_frontend.ViewModel.User.Login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName(value ="code") val code : Int,
    @SerializedName(value = "isSuccess") val isSuccess : Boolean,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val result : String
)
