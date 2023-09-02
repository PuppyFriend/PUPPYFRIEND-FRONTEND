package com.example.puppyfriend_frontend.View.Chatting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.puppyfriend_frontend.Model.Chat
import com.example.puppyfriend_frontend.databinding.ActivityChattingBinding

class ChattingActivity : AppCompatActivity() {
    lateinit var viewBinding : ActivityChattingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityChattingBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initActionBar()

        var chat_list = ArrayList<Chat>()
        var chatRVAdapter = ChatRVAdapter(this, chat_list)
        viewBinding.recyclerviewMessages.adapter = chatRVAdapter
        viewBinding.recyclerviewMessages.layoutManager = LinearLayoutManager(this).apply {
            this.stackFromEnd = true
        }
    }

    fun initActionBar(){
        viewBinding.actionbar.appbarPuppyfriendSns.visibility = View.VISIBLE
        viewBinding.actionbar.appbarMenuBtn.visibility = View.VISIBLE
        viewBinding.actionbar.appbarInfoBtn.visibility = View.VISIBLE
    }

}