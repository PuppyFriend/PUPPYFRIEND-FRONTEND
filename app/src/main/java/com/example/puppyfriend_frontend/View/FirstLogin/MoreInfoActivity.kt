package com.example.puppyfriend_frontend.View.FirstLogin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyfriend_frontend.View.MainActivity
import com.example.puppyfriend_frontend.databinding.ActivityMoreinfoBinding

class MoreInfoActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActivityMoreinfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMoreinfoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnStart.setOnClickListener{
            val receivedPuppyName = intent.getStringExtra("puppy_name")
            val receivedPuppySpecies = intent.getStringExtra("puppy_species")
            val receivedPuppyAge = intent.getStringExtra("puppy_age")
            val receivedPuppyMonth = intent.getStringExtra("puppy_month")
            val receivedPuppyGender = intent.getStringExtra("puppy_gender")
            val receivedPuppyGoal = intent.getStringExtra("puppy_goal")

            val intentToHome = Intent(this, MainActivity::class.java)
            intentToHome.putExtra("puppy_name", receivedPuppyName)
            intentToHome.putExtra("puppy_species", receivedPuppySpecies)
            intentToHome.putExtra("puppy_age", receivedPuppyAge)
            intentToHome.putExtra("puppy_month", receivedPuppyMonth)
            intentToHome.putExtra("puppy_gender", receivedPuppyGender)
            intentToHome.putExtra("puppy_goal", receivedPuppyGoal)

            intentToHome.putExtra("showDialog", true)
            startActivity(intentToHome)

        }

    }




}