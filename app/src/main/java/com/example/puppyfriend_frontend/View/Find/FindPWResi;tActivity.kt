package com.example.puppyfriend_frontend.View.Find

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyfriend_frontend.databinding.ActivityFindPwResultBinding

class FindPWResultActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityFindPwResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFindPwResultBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val findPwActivity = FindPWActivity.findPwActivity

        viewBinding.btnDone.setOnClickListener {
            findPwActivity!!.finish()
            finish()
        }
    }
}