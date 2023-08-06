package com.example.puppyfriend_frontend.View.Chatting

import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.puppyfriend_frontend.Model.ChatProfile
import com.example.puppyfriend_frontend.View.Chatting.Dialog.ChatRoomExitDialog
import com.example.puppyfriend_frontend.databinding.ItemChatProfileBinding

class ChatProfileRVAdapter(private val longClick: () -> Unit,
                           private val chatProfileList : ArrayList<ChatProfile>) :
    RecyclerView.Adapter<ChatProfileRVAdapter.ViewHolder>(){
    lateinit var context: Context
    // 아이템 레이아웃 결합
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemChatProfileBinding = ItemChatProfileBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup, false
        )
        context = viewGroup.context
        return ViewHolder(binding)
    }

    // 아이템 개수
    override fun getItemCount(): Int = chatProfileList.size

    // view에 내용 입력
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(chatProfileList[position], longClick)
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(chatProfileList[position])
            notifyItemChanged(position)
        }
    }

    // 레이아웃 내 view 연결
    inner class ViewHolder(val viewBinding: ItemChatProfileBinding) :
        RecyclerView.ViewHolder(viewBinding.root){
        fun bind(chatProfile: ChatProfile, longclick: () -> Unit) {
            // 데이터처리
            viewBinding.txtProfile.text = chatProfile.name


            if(chatProfile.isClamped)
                viewBinding.swipeItemview.translationX = viewBinding.root.width * -1f/10*3
            else
                viewBinding.swipeItemview.translationX = 0f

            viewBinding.btnAlarm.setOnClickListener {
                viewBinding.btnAlarm.isSelected = !viewBinding.btnAlarm.isSelected
            }

            viewBinding.btnDelete.setOnClickListener {
                val exitDialog = ChatRoomExitDialog(context)
                exitDialog.start()

            }
        }

        fun setClamped(isClamped: Boolean){
            getItem(adapterPosition).isClamped = isClamped
        }

        fun getClamped(): Boolean{
            return getItem(adapterPosition).isClamped
        }
    }

    private fun getItem(position: Int) : ChatProfile{
        return chatProfileList[position]
    }

    interface OnItemClickListener {
        fun onItemClick(chatProfile: ChatProfile)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    private lateinit var itemClickListener: OnItemClickListener
}