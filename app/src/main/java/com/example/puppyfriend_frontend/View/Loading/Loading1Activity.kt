package com.example.puppyfriend_frontend.View.Loading

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.puppyfriend_frontend.databinding.ActivityLoading1Binding

class Loading1Activity : AppCompatActivity() {
    lateinit var viewBinding : ActivityLoading1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoading1Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnNext.setOnClickListener {
            val intent = Intent(this, Loading2Activity::class.java)
            startActivity(intent)
        }
    }
}