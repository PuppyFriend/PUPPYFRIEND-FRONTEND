package com.example.puppyfriend_frontend.View.Chatting

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyfriend_frontend.Model.Chat
import com.example.puppyfriend_frontend.R

class ChatRVAdapter(
    val context : Context,
    val chatList : ArrayList<Chat>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var glide_context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view : View?
        glide_context = parent.context
        return when(viewType){
            1 -> {
                view = LayoutInflater.from(glide_context).inflate(
                    R.layout.item_your_chat, parent, false
                )
                LeftViewHolder(view)
            }
            2 -> {
                view = LayoutInflater.from(glide_context).inflate(
                    R.layout.item_my_chat, parent, false
                )
                RightViewHolder(view)
            }
            3 -> {
                view = LayoutInflater.from(glide_context).inflate(
                    R.layout.item_chat_center, parent, false
                )
                CenterViewHolder(view)
            }
            else -> {
                throw RuntimeException("Error")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(chatList[position].viewType){
            1 -> {
                (holder as LeftViewHolder).bind(chatList[position])
                holder.setIsRecyclable(false)
            }
            2 -> {
                (holder as RightViewHolder).bind(chatList[position])
                holder.setIsRecyclable(false)
            }
            else -> {
                (holder as CenterViewHolder).bind(chatList[position])
                holder.setIsRecyclable(false)
            }
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    fun addItem(chat: Chat) {
        chatList.add(chat)
    }

    //xml을 여러개 사용하려면 오버라이딩 해줘야 함
    override fun getItemViewType(position: Int): Int {
        return chatList[position].viewType
    }

    // 상대방 채팅
    inner class LeftViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val img_profile : ImageView = view.findViewById(R.id.item_your_profile)
        private val txt_name : TextView = view.findViewById(R.id.item_txt_name)
        private val txt_content : TextView = view.findViewById(R.id.item_your_chat_iv)
        private val send_time : TextView = view.findViewById(R.id.item_txt_send_time)
        fun bind(chat: Chat) {

            txt_name.text = chat.senderName
            txt_content.text = chat.content
        }

    }


    // 내 채팅
    inner class RightViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val txt_content : TextView = view.findViewById(R.id.item_my_chat_iv)
        private val send_time : TextView = view.findViewById(R.id.item_txt_send_time)
        fun bind(chat: Chat) {
            txt_content.text = chat.content
        }
    }


    /// 시간, 나갔습니다, 들어왔습니다 등등
    inner class CenterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val time : TextView = view.findViewById(R.id.item_txt_time)
        fun bind(chat: Chat) {
            time.text = chat.sendTime
        }
    }

}