package com.example.puppyfriend_frontend.View.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyfriend_frontend.View.Find.FindIDActivity
import com.example.puppyfriend_frontend.View.Find.FindPWActivity
import com.example.puppyfriend_frontend.View.MainActivity
import com.example.puppyfriend_frontend.ViewModel.User.Login.LoginResult
import com.example.puppyfriend_frontend.ViewModel.User.Login.LoginService
import com.example.puppyfriend_frontend.databinding.ActivityLoginSelfBinding
import kotlin.math.log

class LoginSelfActivity: AppCompatActivity(), LoginResult {
    lateinit var viewBinding: ActivityLoginSelfBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginSelfBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnLogin.setOnClickListener{
            login()
        }

        viewBinding.txtFindId.setOnClickListener {
            val intent = Intent(this, FindIDActivity::class.java)
            startActivity(intent)
        }

        viewBinding.txtFindPw.setOnClickListener {
            val intent = Intent(this, FindPWActivity::class.java)
            startActivity(intent)
        }
    }

    fun login(){
        val loginService = LoginService()
        loginService.setLoginResult(this)
        loginService.login(viewBinding.edittextEmailId.text.toString(), viewBinding.edittextPassword.text.toString())
    }

    override fun loginSuccess(code: Int, message: String) {
        Toast.makeText(this, "로그인이 완료되었습니다.", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        finish()
        startActivity(intent)
    }

    override fun loginFailure(code: Int, message: String) {
        Toast.makeText(this, "로그인에 실패하였습니다..", Toast.LENGTH_SHORT).show()
    }
}