package com.example.puppyfriend_frontend.ViewModel.User.Login

interface LoginResult {
    fun loginSuccess(code: Int, message : String)
    fun loginFailure(code: Int, message : String)
}