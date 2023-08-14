package com.example.puppyfriend_frontend.View.Sns.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyfriend_frontend.View.Sns.model.Story
import com.example.puppyfriend_frontend.databinding.ItemStoryBinding

class StoryAdapter(private val storyList: List<Story>) : RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val itemBinding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return StoryViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = storyList[position]
        holder.bind(story)
    }

    override fun getItemCount(): Int {
        return storyList.size
    }

    inner class StoryViewHolder(private val itemBinding: ItemStoryBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(story: Story) {
            // Character 객체로부터 데이터를 가져와서 뷰에 설정
            itemBinding.textSnsProfileName.text = story.puppyName
            itemBinding.imgSnsProfile.setImageResource(story.image)
        }
    }

}