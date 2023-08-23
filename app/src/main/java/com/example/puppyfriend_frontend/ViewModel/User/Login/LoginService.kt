package com.example.puppyfriend_frontend.ViewModel.User.Login

import android.util.Log
import com.example.puppyfriend_frontend.Model.UserJoinDto
import com.example.puppyfriend_frontend.ViewModel.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class LoginService {
    private lateinit var loginResult: LoginResult

    fun setLoginResult(loginResult: LoginResult){
        this.loginResult = loginResult
    }

    fun login(name : String, password : String){
        val authService = getRetrofit().create(LoginRetrofitInterfaces::class.java)
        authService.login(name, password).enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("RECORD/SUCCESS",response.toString())
                if(response.body() != null){
                    val resp: LoginResponse = response.body()!!
                    Log.d("resp","resp.code : ${resp.code}, resp.result : ${resp.result}")
                    when(resp.code){
                        1000 -> loginResult.loginSuccess(resp.code, resp.result)
//                        201 -> signUpResult.signUpCreated(resp.code, resp.result)
//                        401 -> signUpResult.signUpUnautorized(resp.code, resp.result)
//                        403 -> signUpResult.signUpForbidden(resp.code, resp.result)
                        else -> loginResult.loginFailure(resp.code, resp.result)
                    }
                }else{
                    Log.d("RECORD/FAILURE",  "null")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("RECORD/FAILURE",t.message.toString())
            }
        })

    }
}