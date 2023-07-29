package com.example.puppyfriend_frontend.View.Sns.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.puppyfriend_frontend.View.Sns.model.Photo
import com.example.puppyfriend_frontend.databinding.ItemPhotoBinding

class PhotosAdapter(private val photosList: List<Photo>) :
    RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val viewBinding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photosList[position])
    }

    override fun getItemCount(): Int {
        return photosList.size
    }

    inner class PhotoViewHolder(private val viewBinding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(photo: Photo) {
            Glide.with(viewBinding.root)
                .load(photo.url)
                .into(viewBinding.imageViewPhoto)
        }
    }
}
