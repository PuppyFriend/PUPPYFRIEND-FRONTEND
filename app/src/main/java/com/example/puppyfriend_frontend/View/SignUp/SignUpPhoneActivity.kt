package com.example.puppyfriend_frontend.View.SignUp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyfriend_frontend.databinding.ActivitySignupPhoneBinding

class SignUpPhoneActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivitySignupPhoneBinding
    var email : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySignupPhoneBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val name = intent.getStringExtra("name")
        val nickname = intent.getStringExtra("nickname")
        val uid = intent.getStringExtra("uid")
        val password = intent.getStringExtra("password")
        val gender = intent.getBooleanExtra("gender", false)
        val birth = intent.getStringExtra("birth")

        initActionBar()
        initView()

        viewBinding.btnCertificate.setOnClickListener {
            val intent = Intent(this, SignUpAgreeActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("nickname", nickname)
            intent.putExtra("uid", uid)
            intent.putExtra("password", password)
            intent.putExtra("gender", gender)
            intent.putExtra("birth", birth)
            intent.putExtra("email", email)
            startActivity(intent)
        }
    }

    fun initActionBar(){
        viewBinding.actionbar.appbarPageNameLeftTv.text = "휴대폰 인증"
        viewBinding.actionbar.appbarBackBtn.setOnClickListener { onBackPressed() }
    }

    fun initView(){
        // 전화번호
        viewBinding.textInputEditPhoneNumber.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable?) {
                email = viewBinding.textInputEditPhoneNumber.text.toString()
                //name = viewBinding.textInputEditName.text.toString()
            }


        })

        viewBinding.textInputEditPhoneNumber.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable?) {
                // 오류인 경우
                //viewBinding.textInputName.error = "이미 존재하는 닉네임입니다."
            }
        })
    }
}