package com.example.puppyfriend_frontend.View.Loading

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.puppyfriend_frontend.databinding.ActivityLoading1Binding
import com.example.puppyfriend_frontend.databinding.ActivityLoading2Binding

class Loading2Activity : AppCompatActivity() {
    lateinit var viewBinding : ActivityLoading2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoading2Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnNext.setOnClickListener {
            val intent = Intent(this, Loading3Activity::class.java)
            startActivity(intent)
        }
    }
}