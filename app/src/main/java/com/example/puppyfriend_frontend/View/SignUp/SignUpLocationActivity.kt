package com.example.puppyfriend_frontend.View.SignUp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyfriend_frontend.View.Login.LoginActivity
import com.example.puppyfriend_frontend.databinding.ActivitySignupLocationBinding

class SignUpLocationActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivitySignupLocationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding =  ActivitySignupLocationBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        viewBinding.btnDone.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            finishAffinity()
            startActivity(intent)
        }

        initActionBar()
    }

    fun initActionBar(){
        viewBinding.actionbar.appbarPageNameLeftTv.text = "위치정보"
        viewBinding.actionbar.appbarBackBtn.setOnClickListener { onBackPressed() }
    }
}