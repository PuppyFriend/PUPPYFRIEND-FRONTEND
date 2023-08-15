package com.example.puppyfriend_frontend.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.View.Sns.SnsFragment
import com.example.puppyfriend_frontend.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // HomeFragment를 로드하여 fragmentContainer에 추가합니다.
        val homeFragment = SnsFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, homeFragment)
            .addToBackStack(null)
            .commit()
    }
}