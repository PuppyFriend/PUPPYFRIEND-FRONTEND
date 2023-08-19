package com.example.puppyfriend_frontend.View

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyfriend_frontend.R

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val toggleButton: ToggleButton = findViewById(R.id.toggleButton)
        val hiddenLayout: LinearLayout = findViewById(R.id.hiddenLayout)

        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                hiddenLayout.visibility = View.VISIBLE
            } else {
                hiddenLayout.visibility = View.GONE
            }
        }

    }
}