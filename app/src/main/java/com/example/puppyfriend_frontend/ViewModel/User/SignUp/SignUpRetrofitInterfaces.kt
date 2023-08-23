package com.example.puppyfriend_frontend.ViewModel.User.SignUp

import com.example.puppyfriend_frontend.Model.UserJoinDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpRetrofitInterfaces {
    @POST("/api/v1/users/join")
    fun signUp(@Body userJoinDto : UserJoinDto) : Call<SignUpResponse>
}