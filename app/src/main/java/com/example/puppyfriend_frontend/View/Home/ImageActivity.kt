package com.example.puppyfriend_frontend.View.Home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.databinding.ActvitiyImageBinding

class ImageActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActvitiyImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActvitiyImageBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // 배경을 투명하게 설정
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val imgResId = R.drawable.img_real_circle_dog          // 이미지 리소스 id를 가져오는 로직

        viewBinding.imgFull.setImageResource(imgResId)
        viewBinding.outLayout.setOnClickListener{
            finish()                // 외부 레이아웃 클릭 시 Activity 종료
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()                    // 뒤로가기 버튼 클릭 시 Activity 종료
    }   
}