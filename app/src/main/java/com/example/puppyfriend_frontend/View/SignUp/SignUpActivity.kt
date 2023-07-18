package com.example.puppyfriend_frontend.View.SignUp

import android.graphics.Outline
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewOutlineProvider
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat.setElevation
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import com.example.puppyfriend_frontend.databinding.ActivitySignupBinding
import com.google.android.material.shape.MaterialShapeUtils.setElevation

class SignUpActivity: AppCompatActivity() {
    lateinit var viewBinding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initView()
    }

    fun initView(){
        with(viewBinding){
            viewBinding.textInputEditName.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(s: Editable?) {

                }


            })

            viewBinding.textInputEditName.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    // 오류인 경우
                    viewBinding.textInputName.error = "이미 존재하는 닉네임입니다."
                }


            })
        }
    }
}