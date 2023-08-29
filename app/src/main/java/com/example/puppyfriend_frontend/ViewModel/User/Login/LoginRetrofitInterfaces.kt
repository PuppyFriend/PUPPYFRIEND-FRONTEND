package com.example.puppyfriend_frontend.ViewModel.User.Login

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginRetrofitInterfaces {
    @POST("/api/v1/users/login")
    fun login(@Query("name") name : String, @Query("password") password : String ) : Call<LoginResponse>
}