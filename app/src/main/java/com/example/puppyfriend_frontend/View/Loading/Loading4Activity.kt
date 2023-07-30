package com.example.puppyfriend_frontend.View.Loading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.puppyfriend_frontend.databinding.ActivityLoading1Binding
import com.example.puppyfriend_frontend.databinding.ActivityLoading2Binding
import com.example.puppyfriend_frontend.databinding.ActivityLoading4Binding

class Loading4Activity : AppCompatActivity() {
    lateinit var viewBinding : ActivityLoading4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoading4Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}