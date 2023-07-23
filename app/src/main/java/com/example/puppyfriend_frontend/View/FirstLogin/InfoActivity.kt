package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.puppyfriend_frontend.View.Home.HomeActivity
import com.example.puppyfriend_frontend.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityInfoBinding

    companion object {
        private const val INFO_ACTIVITY_REQUEST_CODE = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnArrow.setOnClickListener {
            val puppyName = viewBinding.editPuppyName.text.toString()
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("puppy_name", puppyName)
            startActivity(intent)
        }

        viewBinding.btnArrow.setOnClickListener {
            val puppyName = viewBinding.editPuppyName.text.toString()
            val intent = Intent(this, MoreInfoActivity::class.java)
            intent.putExtra("puppy_name", puppyName)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK)
        super.onBackPressed()
    }
}
