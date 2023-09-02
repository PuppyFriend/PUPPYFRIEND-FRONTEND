package com.example.puppyfriend_frontend.View.Chatting.Dialog

import android.app.Dialog
import android.content.Context
import android.view.Window
import androidx.appcompat.widget.AppCompatButton
import com.example.puppyfriend_frontend.R

class ChatRoomExitDialog(context : Context) {
    private val dlg = Dialog(context)
    private lateinit var btn_cancel : AppCompatButton
    private lateinit var btn_ok : AppCompatButton

    fun start(){
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(R.layout.dialog_chat_exit_chatroom)
        dlg.setCancelable(false)

        btn_cancel = dlg.findViewById(R.id.btn_cancel)
        btn_ok = dlg.findViewById(R.id.btn_ok)

        btn_cancel.setOnClickListener {
            dlg.dismiss()
        }

        btn_ok.setOnClickListener {
            // 채팅방 나가기 처리
        }
    }
}