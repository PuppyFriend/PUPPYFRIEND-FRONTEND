package com.example.puppyfriend_frontend.View.Sns.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyfriend_frontend.View.Sns.model.Character  //*
import com.example.puppyfriend_frontend.databinding.ItemHashtagBinding

class CharacterAdapter(private val characterList: List<Character>) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemBinding = ItemHashtagBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return CharacterViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characterList[position]
        holder.bind(character)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    inner class CharacterViewHolder(private val itemBinding: ItemHashtagBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        private val hashtagTextView: TextView = itemBinding.textHashtag

        fun bind(character: Character) {
            // Character 객체로부터 데이터를 가져와서 뷰에 설정
            hashtagTextView.text = character.hashtagText
        }
    }

}