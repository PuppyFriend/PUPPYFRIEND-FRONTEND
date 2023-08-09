package com.example.puppyfriend_frontend.View.Chatting.Dialog

import android.app.Dialog
import android.content.Context
import android.view.Window
import androidx.appcompat.widget.AppCompatButton
import com.example.puppyfriend_frontend.R

class ChatNotificationDialog(context : Context) {
    private val dlg = Dialog(context)
    private lateinit var btn_cancel : AppCompatButton
    private lateinit var btn_chat : AppCompatButton

    fun start(){
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(R.layout.dialog_chatting_notification)
        dlg.setCancelable(false)

        btn_cancel = dlg.findViewById(R.id.btn_cancel)
        btn_chat = dlg.findViewById(R.id.btn_chat)

        btn_cancel.setOnClickListener {
            dlg.dismiss()
            // 채팅화면에서 나가기 처리
        }

        btn_chat.setOnClickListener {
            dlg.dismiss()
        }
    }
}