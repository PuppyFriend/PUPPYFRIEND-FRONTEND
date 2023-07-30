package com.example.puppyfriend_frontend.View.Loading

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.puppyfriend_frontend.databinding.ActivityLoading1Binding
import com.example.puppyfriend_frontend.databinding.ActivityLoading2Binding
import com.example.puppyfriend_frontend.databinding.ActivityLoading3Binding

class Loading3Activity : AppCompatActivity() {
    lateinit var viewBinding : ActivityLoading3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoading3Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnNext.setOnClickListener {
            val intent = Intent(this, Loading4Activity::class.java)
            startActivity(intent)
        }
    }
}