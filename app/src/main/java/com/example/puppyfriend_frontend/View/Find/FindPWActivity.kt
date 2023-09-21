package com.example.puppyfriend_frontend.View.Find

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyfriend_frontend.databinding.ActivityFindIdBinding
import com.example.puppyfriend_frontend.databinding.ActivityFindPwBinding

class FindPWActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityFindPwBinding

    companion object{
        var findPwActivity : FindPWActivity? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFindPwBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        findPwActivity = this

        viewBinding.btnNext.setOnClickListener {
            val intent = Intent(this, FindPWResultActivity::class.java)
            startActivity(intent)
        }
    }
}