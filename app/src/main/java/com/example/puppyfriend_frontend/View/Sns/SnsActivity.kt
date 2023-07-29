package com.example.puppyfriend_frontend.View.Sns

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyfriend_frontend.databinding.ActivitySnsBinding

class SnsActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActivitySnsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySnsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // 이미지를 배경에 맞게 자른다.
        viewBinding.imgSnsPost.clipToOutline = true

        // 토클 클릭에 view hidden
        viewBinding.togglebtnSnsTriangle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewBinding.toggleHiddenView.visibility = View.VISIBLE
            } else {
                viewBinding.toggleHiddenView.visibility = View.GONE
            }
        }

        // 토글 on 시에 게시글 보다 위에 toggleView가 뜨게 설정
        viewBinding.toggleHiddenView.bringToFront();

        // 토글버튼 클릭시 유지
        viewBinding.togglebtnSnsTriangle.setOnClickListener{
            viewBinding.togglebtnSnsTriangle.isSelected = viewBinding.togglebtnSnsTriangle.isSelected != true
        }

    }
}