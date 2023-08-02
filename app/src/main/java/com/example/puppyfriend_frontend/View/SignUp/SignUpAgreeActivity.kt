package com.example.puppyfriend_frontend.View.SignUp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyfriend_frontend.databinding.ActivitySignupAgreeBinding

class SignUpAgreeActivity: AppCompatActivity() {
    lateinit var viewBinding: ActivitySignupAgreeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySignupAgreeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnNext.setOnClickListener {
            val intent = Intent(this, SignUpLocationActivity::class.java)
            startActivity(intent)
        }
    }
}