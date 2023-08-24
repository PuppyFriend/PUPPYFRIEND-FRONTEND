package com.example.puppyfriend_frontend.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
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
                        it.setIcon(R.drawable.icon_bottom_home_selected)
                        viewBinding.navBottom.menu.findItem(R.id.menu_walk).setIcon(R.drawable.icon_bottom_map)
                        viewBinding.navBottom.menu.findItem(R.id.menu_sns).setIcon(R.drawable.icon_bottom_user)
                        viewBinding.navBottom.menu.findItem(R.id.menu_chatting).setIcon(R.drawable.icon_bottom_chatting)
                        viewBinding.navBottom.menu.findItem(R.id.menu_around).setIcon(R.drawable.icon_bottom_around)

                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.fragmentContainer.id, HomeFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_walk -> {
                        it.setIcon(R.drawable.icon_bottom_walk_selected)
                        viewBinding.navBottom.menu.findItem(R.id.menu_home).setIcon(R.drawable.icon_bottom_home)
                        viewBinding.navBottom.menu.findItem(R.id.menu_sns).setIcon(R.drawable.icon_bottom_user)
                        viewBinding.navBottom.menu.findItem(R.id.menu_chatting).setIcon(R.drawable.icon_bottom_chatting)
                        viewBinding.navBottom.menu.findItem(R.id.menu_around).setIcon(R.drawable.icon_bottom_around)

                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.fragmentContainer.id, WalkFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_sns -> {
                        it.setIcon(R.drawable.icon_bottom_sns_selected)
                        viewBinding.navBottom.menu.findItem(R.id.menu_home).setIcon(R.drawable.icon_bottom_home)
                        viewBinding.navBottom.menu.findItem(R.id.menu_walk).setIcon(R.drawable.icon_bottom_map)
                        viewBinding.navBottom.menu.findItem(R.id.menu_chatting).setIcon(R.drawable.icon_bottom_chatting)
                        viewBinding.navBottom.menu.findItem(R.id.menu_around).setIcon(R.drawable.icon_bottom_around)

                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.fragmentContainer.id, SnsFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_chatting -> {
                        it.setIcon(R.drawable.icon_bottom_chatting_selected)
                        viewBinding.navBottom.menu.findItem(R.id.menu_home).setIcon(R.drawable.icon_bottom_home)
                        viewBinding.navBottom.menu.findItem(R.id.menu_walk).setIcon(R.drawable.icon_bottom_map)
                        viewBinding.navBottom.menu.findItem(R.id.menu_sns).setIcon(R.drawable.icon_bottom_user)
                        viewBinding.navBottom.menu.findItem(R.id.menu_around).setIcon(R.drawable.icon_bottom_around)

                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.fragmentContainer.id, ChatFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_around -> {
                        it.setIcon(R.drawable.icon_bottom_around_selected)
                        viewBinding.navBottom.menu.findItem(R.id.menu_home).setIcon(R.drawable.icon_bottom_home)
                        viewBinding.navBottom.menu.findItem(R.id.menu_walk).setIcon(R.drawable.icon_bottom_map)
                        viewBinding.navBottom.menu.findItem(R.id.menu_sns).setIcon(R.drawable.icon_bottom_user)
                        viewBinding.navBottom.menu.findItem(R.id.menu_chatting).setIcon(R.drawable.icon_bottom_chatting)

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

