package com.example.puppyfriend_frontend.ViewModel.User.SignUp

import android.util.Log
import com.example.puppyfriend_frontend.Model.UserJoinDto
import com.example.puppyfriend_frontend.ViewModel.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class SignUpService {
    private lateinit var signUpResult: SignUpResult

    fun setSignUpResult(signUpResult: SignUpResult){
        this.signUpResult = signUpResult
    }

    fun signUp(user: UserJoinDto){
        val authService = getRetrofit().create(SignUpRetrofitInterfaces::class.java)
        authService.signUp(user).enqueue(object: Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                Log.d("RECORD/SUCCESS",response.toString())
                if(response.body() != null){
                    val resp: SignUpResponse = response.body()!!
                    Log.d("resp","resp.code : ${resp.code}, resp.result : ${resp.result}")
                    when(resp.code){
                        1000 -> signUpResult.signUpSuccess(resp.code, resp.result)
//                        201 -> signUpResult.signUpCreated(resp.code, resp.result)
//                        401 -> signUpResult.signUpUnautorized(resp.code, resp.result)
//                        403 -> signUpResult.signUpForbidden(resp.code, resp.result)
                        else -> signUpResult.signUpFailure(resp.code, resp.result)
                    }
                }else{
                    Log.d("RECORD/FAILURE",  "null")
                }
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                Log.d("RECORD/FAILURE",t.message.toString())
            }
        })

    }
}