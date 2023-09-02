package com.example.puppyfriend_frontend.View.SignUp

import android.content.Context
import android.content.Intent
import android.graphics.Outline
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat.setElevation
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.databinding.ActivitySignupBinding
import com.google.android.material.shape.MaterialShapeUtils.setElevation
import com.google.gson.annotations.SerializedName

class SignUpActivity: AppCompatActivity() {
    lateinit var viewBinding: ActivitySignupBinding
    private var birth_year : String = ""
    private var birth_month : String = ""
    private var birth_day : String = ""
    private var gender : Boolean = false
    private var name : String = ""
    private var nickname : String = ""
    private var password : String = ""
    private var uid : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initActionBar()
        initView()
        setupSpinnerText()
        setupSpinnerHandler()

        viewBinding.btnNext.setOnClickListener {
            val intent = Intent(this, SignUpPhoneActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("nickname", nickname)
            intent.putExtra("uid", uid)
            intent.putExtra("password", password)
            intent.putExtra("gender", gender)
            intent.putExtra("birth", "$birth_year-$birth_month-$birth_day")
            startActivity(intent)
        }
    }

    fun initActionBar(){
        viewBinding.actionbar.appbarPageNameLeftTv.text = "회원가입"
        viewBinding.actionbar.appbarBackBtn.setOnClickListener { onBackPressed() }
    }

    fun initView(){
//        with(viewBinding){
        // 이름
            viewBinding.textInputEditName.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    name = viewBinding.textInputEditName.text.toString()
                }


            })

            viewBinding.textInputEditName.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    // 오류인 경우
                    //viewBinding.textInputName.error = "이미 존재하는 닉네임입니다."
                }
            })

        // 닉네임
        viewBinding.textInputEditNickname.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable?) {
                nickname = viewBinding.textInputEditNickname.text.toString()
            }


        })

        viewBinding.textInputEditNickname.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable?) {
                // 오류인 경우
//                viewBinding.textInputName.error = "이미 존재하는 닉네임입니다."
            }
        })

        // 아이디
        viewBinding.textInputEditId.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable?) {
                uid = viewBinding.textInputEditId.text.toString()
            }


        })

        viewBinding.textInputEditId.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable?) {
                // 오류인 경우
                //viewBinding.textInputId.error = "이미 존재하는 닉네임입니다."
            }
        })

        // 패스워드
        viewBinding.textInputEditPassword.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable?) {
                password = viewBinding.textInputEditPassword.text.toString()
            }


        })

        viewBinding.textInputEditPassword.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable?) {
                // 오류인 경우
                //viewBinding.textInputPassword.error = "이미 존재하는 닉네임입니다."
            }
        })

//        }
    }

    private fun setupSpinnerText() {
        // 연도 연결
        val year_item = resources.getStringArray(R.array.birth_year)
        val year_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, year_item)
        viewBinding.textInputBirthYear.adapter = year_adapter

        // 월 연결
        val month_item = resources.getStringArray(R.array.birth_month)
        val month_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, month_item)
        viewBinding.textInputBirthMonth.adapter = month_adapter

        // 일 연결
        val day_item = resources.getStringArray(R.array.birth_day)
        val day_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, day_item)
        viewBinding.textInputBirthDay.adapter = day_adapter

        val gender_item = resources.getStringArray(R.array.sexual)
        val gender_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, gender_item)
        viewBinding.textInputSexual.adapter = gender_adapter
    }


    private fun setupSpinnerHandler() {
        viewBinding.textInputBirthYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    val year_item = resources.getStringArray(R.array.birth_year)
                    birth_year = year_item[position]
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        viewBinding.textInputBirthMonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    val month_item = resources.getStringArray(R.array.birth_month)
                    if(month_item[position].toInt() < 10){
                        birth_month = "0" + month_item[position]
                    }else{
                        birth_month = month_item[position]
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        viewBinding.textInputBirthDay.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    val day_item = resources.getStringArray(R.array.birth_day)
                    if(day_item[position].toInt() < 10){
                        birth_day = "0" + day_item[position]
                    }else{
                        birth_day = day_item[position]
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        viewBinding.textInputSexual.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 1) {
                    gender = true
                }
                else if(position == 2){
                    gender = false
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

}