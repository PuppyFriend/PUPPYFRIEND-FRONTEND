package com.example.puppyfriend_frontend.View.Around

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.puppyfriend_frontend.databinding.ActivityAroundsearchBinding

class AroundsearchActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAroundsearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = ActivityAroundsearchBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
    }
}