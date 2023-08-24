package com.example.puppyfriend_frontend.ViewModel.User.SignUp

interface SignUpResult {
    fun signUpSuccess(code: Int, message : String)
    fun signUpCreated(code: Int, message : String)
    fun signUpUnautorized(code: Int, message : String)
    fun signUpForbidden(code: Int, message : String)
    fun signUpFailure(code: Int, message : String)
}