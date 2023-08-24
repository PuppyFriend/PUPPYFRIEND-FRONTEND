package com.example.puppyfriend_frontend.ViewModel.User.SignUp

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName(value ="code") val code : Int,
    @SerializedName(value = "isSuccess") val isSuccess : Boolean,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val result : String
)