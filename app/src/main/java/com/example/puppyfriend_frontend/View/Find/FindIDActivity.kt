package com.example.puppyfriend_frontend.View.Find

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyfriend_frontend.databinding.ActivityFindIdBinding

class FindIDActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityFindIdBinding

    companion object{
        var findIdActivity : FindIDActivity? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFindIdBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        findIdActivity = this

        viewBinding.btnNext.setOnClickListener{
            val intent = Intent(this, FindIDResultActivity::class.java)
            startActivity(intent)
        }
    }
}