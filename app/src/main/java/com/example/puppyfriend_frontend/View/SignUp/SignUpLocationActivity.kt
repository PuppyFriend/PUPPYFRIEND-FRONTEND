package com.example.puppyfriend_frontend.View.SignUp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyfriend_frontend.databinding.ActivitySignupLocationBinding

class SignUpLocationActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivitySignupLocationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding =  ActivitySignupLocationBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

    }
}