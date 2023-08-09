package com.example.puppyfriend_frontend.View.SignUp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyfriend_frontend.databinding.ActivitySignupAgreeBinding

class SignUpAgreeActivity: AppCompatActivity() {
    lateinit var viewBinding: ActivitySignupAgreeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySignupAgreeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initActionBar()

        viewBinding.btnAgreeAll.setOnClickListener {
            viewBinding.btnAgreeAll.isSelected = !viewBinding.btnAgreeAll.isSelected
            // 나머지 버튼들 체크 상태 모두 바꾸기
            if(viewBinding.btnAgreeAll.isSelected){
                viewBinding.btnAgreeFirst.isSelected = true
                viewBinding.btnAgreeSecond.isSelected = true
                viewBinding.btnAgreeThird.isSelected = true
                viewBinding.btnNext.setBackgroundColor((Color.parseColor("#F6C0CA")))
            }else{
                viewBinding.btnAgreeFirst.isSelected = false
                viewBinding.btnAgreeSecond.isSelected = false
                viewBinding.btnAgreeThird.isSelected = false
                viewBinding.btnNext.setBackgroundColor((Color.parseColor("#C4C4C4")))
            }
        }

        viewBinding.btnAgreeFirst.setOnClickListener {
            viewBinding.btnAgreeFirst.isSelected = !viewBinding.btnAgreeFirst.isSelected

            // 모두 동의 선택인 상태일 때 클릭 취소하면 상태 바꿔주기
            if(!viewBinding.btnAgreeFirst.isSelected && viewBinding.btnAgreeAll.isSelected){
                viewBinding.btnAgreeAll.isSelected = false
                viewBinding.btnNext.setBackgroundColor((Color.parseColor("#C4C4C4")))
            }

            if(viewBinding.btnAgreeFirst.isSelected && viewBinding.btnAgreeSecond.isSelected && viewBinding.btnAgreeThird.isSelected) {
                viewBinding.btnAgreeAll.isSelected = true
                viewBinding.btnNext.setBackgroundColor((Color.parseColor("#F6C0CA")))
            }
        }

        viewBinding.btnAgreeSecond.setOnClickListener {
            viewBinding.btnAgreeSecond.isSelected = !viewBinding.btnAgreeSecond.isSelected

            // 모두 동의 선택인 상태일 때 클릭 취소하면 상태 바꿔주기
            if(!viewBinding.btnAgreeSecond.isSelected && viewBinding.btnAgreeAll.isSelected){
                viewBinding.btnAgreeAll.isSelected = false
                viewBinding.btnNext.setBackgroundColor((Color.parseColor("#C4C4C4")))
            }

            if(viewBinding.btnAgreeFirst.isSelected && viewBinding.btnAgreeSecond.isSelected && viewBinding.btnAgreeThird.isSelected) {
                viewBinding.btnAgreeAll.isSelected = true
                viewBinding.btnNext.setBackgroundColor((Color.parseColor("#F6C0CA")))
            }
        }

        viewBinding.btnAgreeThird.setOnClickListener {
            viewBinding.btnAgreeThird.isSelected = !viewBinding.btnAgreeThird.isSelected

            // 모두 동의 선택인 상태일 때 클릭 취소하면 상태 바꿔주기
            if(!viewBinding.btnAgreeThird.isSelected && viewBinding.btnAgreeAll.isSelected){
                viewBinding.btnAgreeAll.isSelected = false
                viewBinding.btnNext.setBackgroundColor((Color.parseColor("#C4C4C4")))
            }

            if(viewBinding.btnAgreeFirst.isSelected && viewBinding.btnAgreeSecond.isSelected && viewBinding.btnAgreeThird.isSelected) {
                viewBinding.btnAgreeAll.isSelected = true
                viewBinding.btnNext.setBackgroundColor((Color.parseColor("#F6C0CA")))
            }
        }

        viewBinding.btnNext.setOnClickListener {
            if (!viewBinding.btnAgreeAll.isSelected){
                Toast.makeText(this, "약관동의를 진행해주세요", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, SignUpLocationActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun initActionBar(){
        viewBinding.actionbar.appbarPageNameLeftTv.text = "위치정보"
        viewBinding.actionbar.appbarBackBtn.setOnClickListener { onBackPressed() }
    }
}