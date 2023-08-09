package com.example.puppyfriend_frontend.View.Sns.adapter

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.net.Uri
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.View.Sns.model.Posting  //*
import com.example.puppyfriend_frontend.databinding.ItemPostingBinding

class PostingAdapter(private val postingList: List<Posting>) : RecyclerView.Adapter<PostingAdapter.PostingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostingViewHolder {
        val itemBinding = ItemPostingBinding.inflate(LayoutInflater.from(parent.context),parent, false)

        itemBinding.imgBlueMemo.clipToOutline = true

        return PostingViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PostingViewHolder, position: Int) {
        val posting = postingList[position]
        holder.bind(posting)

    }

    override fun getItemCount(): Int {
        return postingList.size
    }

    inner class PostingViewHolder(private val itemBinding: ItemPostingBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(posting: Posting) {
            // Posting 객체로부터 데이터를 가져와서 뷰에 설정
            itemBinding.textBlueMemoDate.text = posting.date     // local date를 string으로 변환
            itemBinding.textBlueMemoComment.text = posting.content
            itemBinding.imgBlueMemo.setImageURI(Uri.parse(posting.image))

            val backgroundColor = ColorDrawable(posting.contentBackgroundColor)
            itemBinding.viewBlueMemo.background = backgroundColor
        }
    }

}