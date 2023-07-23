package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyfriend_frontend.View.Home.HomeActivity
import com.example.puppyfriend_frontend.databinding.ActivityMoreinfoBinding

class MoreInfoActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActivityMoreinfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMoreinfoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnStart.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

    }



}