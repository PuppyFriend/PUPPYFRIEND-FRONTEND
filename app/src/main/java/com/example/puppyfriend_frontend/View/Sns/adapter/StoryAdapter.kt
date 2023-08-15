package com.example.puppyfriend_frontend.View.Sns.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyfriend_frontend.View.Sns.model.Story
import com.example.puppyfriend_frontend.databinding.ItemMystoryBinding
import com.example.puppyfriend_frontend.databinding.ItemStoryBinding

class StoryAdapter(private val storyList: List<Story>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_MY_STORY = 0
        private const val VIEW_TYPE_OTHER_STORY = 1
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            VIEW_TYPE_MY_STORY -> {
                val itemBinding = ItemMystoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MyStoryViewHolder(itemBinding)
            }
            VIEW_TYPE_OTHER_STORY -> {
                val itemBinding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                StoryViewHolder(itemBinding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val story = storyList[position]
        when (holder) {
            is MyStoryViewHolder -> holder.bindMyStory(story)
            is StoryViewHolder -> holder.bind(story)
        }

        val layoutParams = holder.itemView.layoutParams
        layoutParams.width = 154
        holder.itemView.requestLayout()
    }

    override fun getItemCount(): Int {
        return storyList.size
    }

    override fun getItemViewType(position: Int): Int {
        // 첫 번째 아이템은 My Story로, 나머지 아이템은 Other Story로 구분
        return if (position == 0) VIEW_TYPE_MY_STORY else VIEW_TYPE_OTHER_STORY
    }

    inner class MyStoryViewHolder(private val itemBinding: ItemMystoryBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bindMyStory(story: Story) {
            // My Story 아이템의 바인딩 처리
            itemBinding.imgSnsMyStory.clipToOutline = true
            itemBinding.imgSnsMyStory.setImageResource(story.image)
        }
    }



    inner class StoryViewHolder(private val itemBinding: ItemStoryBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(story: Story) {
            itemBinding.imgSnsStory.clipToOutline = true
            // Character 객체로부터 데이터를 가져와서 뷰에 설정
            itemBinding.textSnsStoryName.text = story.puppyName
            itemBinding.imgSnsStory.setImageResource(story.image)
        }
    }

}