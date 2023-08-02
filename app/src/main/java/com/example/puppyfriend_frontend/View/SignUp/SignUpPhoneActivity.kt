package com.example.puppyfriend_frontend.View.SignUp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyfriend_frontend.databinding.ActivitySignupPhoneBinding

class SignUpPhoneActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivitySignupPhoneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySignupPhoneBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnCertificate.setOnClickListener {
            val intent = Intent(this, SignUpAgreeActivity::class.java)
            startActivity(intent)
        }
    }
}