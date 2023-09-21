package com.example.puppyfriend_frontend.View.Find

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyfriend_frontend.databinding.ActivityFindIdResultBinding

class FindIDResultActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityFindIdResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFindIdResultBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val findIdActivity = FindIDActivity.findIdActivity

        viewBinding.btnFindPw.setOnClickListener {
            val intent = Intent(this, FindPWActivity::class.java)
            startActivity(intent)
        }

        viewBinding.btnLogin.setOnClickListener {
            findIdActivity!!.finish()
            finish()
        }
    }
}