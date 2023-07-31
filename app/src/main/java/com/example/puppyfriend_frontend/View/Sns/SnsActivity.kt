package com.example.puppyfriend_frontend.View.Sns

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.databinding.ActivitySnsBinding

class SnsActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActivitySnsBinding
    private lateinit var toggleHiddenFragment: ToggleHiddenFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySnsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // 이미지를 배경에 맞게 자른다.
        viewBinding.imgSnsPost.clipToOutline = true

        // 토글버튼 클릭시 유지
        viewBinding.togglebtnSnsTriangle.setOnClickListener{
            viewBinding.togglebtnSnsTriangle.isSelected = viewBinding.togglebtnSnsTriangle.isSelected != true
        }

        // toggle_hidden_view
        // Fragment를 동적으로 추가
        toggleHiddenFragment = ToggleHiddenFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, toggleHiddenFragment)
            .commit()

        // 토클 클릭에 view hidden
        viewBinding.togglebtnSnsTriangle.setOnCheckedChangeListener { _, isChecked ->
            val toggleFragmentView = toggleHiddenFragment.view
            val visibility = if (isChecked) View.VISIBLE else View.GONE

            toggleFragmentView?.apply {
                findViewById<View>(R.id.recyclerView_character).visibility = visibility
                findViewById<View>(R.id.text_character).visibility = visibility
                findViewById<View>(R.id.recyclerView_activity).visibility = visibility
                findViewById<View>(R.id.text_activity).visibility = visibility
                findViewById<View>(R.id.view_toggle_hidden).visibility = visibility

            }
                viewBinding.fragmentContainer.visibility = visibility
        }
    }
}