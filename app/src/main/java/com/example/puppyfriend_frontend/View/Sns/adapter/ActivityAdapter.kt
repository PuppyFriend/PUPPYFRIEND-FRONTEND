package com.example.puppyfriend_frontend.View.Sns.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyfriend_frontend.View.Sns.model.Activity
import com.example.puppyfriend_frontend.databinding.ItemHashtagBinding

class ActivityAdapter(private val activityList: List<Activity>) : RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val itemBinding = ItemHashtagBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ActivityViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val activity = activityList[position]
        holder.bind(activity)
    }

    override fun getItemCount(): Int {
        return activityList.size
    }

    inner class ActivityViewHolder(private val itemBinding: ItemHashtagBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        private val hashtagTextView: TextView = itemBinding.textHashtag

        fun bind(activity: Activity) {
            // Character 객체로부터 데이터를 가져와서 뷰에 설정
            hashtagTextView.text = activity.hashtagText
        }
    }

}