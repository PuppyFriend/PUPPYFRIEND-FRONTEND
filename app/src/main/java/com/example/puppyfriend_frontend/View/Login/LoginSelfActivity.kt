package com.example.puppyfriend_frontend.View.Login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyfriend_frontend.databinding.ActivityLoginSelfBinding
class LoginSelfActivity: AppCompatActivity() {
    lateinit var viewBinding: ActivityLoginSelfBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginSelfBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

    }
}