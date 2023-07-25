package com.example.puppyfriend_frontend.View.Around

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.databinding.ActivityAroundBinding
import com.example.puppyfriend_frontend.databinding.ActivitySignupBinding

class AroundActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAroundBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = ActivityAroundBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        initView()
        initActionBar()
        viewBinding.txtRange.setOnClickListener {
            viewBinding.view.visibility=View.VISIBLE
            viewBinding.linearRangePopup.visibility=View.VISIBLE
        }
        viewBinding.view.setOnClickListener {
            viewBinding.view.visibility=View.GONE
            viewBinding.linearRangePopup.visibility=View.GONE
        }
    }
    fun initActionBar(){
        viewBinding.actionbar.appbarPuppyfriendSns.visibility = View.VISIBLE
    }
    fun initView(){

    }
}