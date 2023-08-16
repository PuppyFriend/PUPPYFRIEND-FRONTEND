package com.example.puppyfriend_frontend.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.View.Around.AroundFragment
import com.example.puppyfriend_frontend.View.Chatting.ChatFragment
import com.example.puppyfriend_frontend.View.Home.HomeFragment
import com.example.puppyfriend_frontend.View.Sns.SnsFragment
import com.example.puppyfriend_frontend.View.Walk.WalkFragment
import com.example.puppyfriend_frontend.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initActionBar()

        supportFragmentManager
            .beginTransaction()
            .replace(viewBinding.fragmentContainer.id, HomeFragment())
            .commitAllowingStateLoss()

        viewBinding.navBottom.run {
            setOnItemSelectedListener {
                when (it.itemId){
                    R.id.menu_home -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.fragmentContainer.id, HomeFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_walk -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.fragmentContainer.id, WalkFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_sns -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.fragmentContainer.id, SnsFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_chatting -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.fragmentContainer.id, ChatFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_around -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.fragmentContainer.id, AroundFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            //처음 실행시 자동으로 home 화면을 가르키게 됨.
            selectedItemId = R.id.menu_home
        }
    }

    fun initActionBar(){
        viewBinding.actionbar.appbarMenuBtn.visibility = View.VISIBLE
        viewBinding.actionbar.appbarPuppyfriendSns.visibility = View.VISIBLE
        viewBinding.actionbar.appbarInfoBtn.visibility = View.VISIBLE
    }
}