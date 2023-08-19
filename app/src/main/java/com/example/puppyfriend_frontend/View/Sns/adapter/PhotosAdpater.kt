package com.example.puppyfriend_frontend.View.Sns.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.View.Sns.model.Photo
import com.example.puppyfriend_frontend.databinding.ItemPhotoBinding

class PhotosAdapter(
    private val photosList: List<Photo>,
    private val onPhotoClickListener: (Photo) -> Unit,
    private val onIconClickListener: () -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_ICON = 0
        private const val VIEW_TYPE_PHOTO = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ICON -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_camera, parent, false)
                IconViewHolder(view)
            }
            VIEW_TYPE_PHOTO -> {
                val viewBinding =
                    ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                PhotoViewHolder(viewBinding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is IconViewHolder -> holder.bind(onIconClickListener)
            is PhotoViewHolder -> holder.bind(photosList[position - 1]) // Subtract 1 to skip the icon
        }
    }

    override fun getItemCount(): Int {
        return photosList.size + 1 // Add 1 to account for the icon item
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_ICON else VIEW_TYPE_PHOTO
    }

    inner class IconViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(onIconClickListener: () -> Unit) {
            // Add click listener for the new icon
            itemView.setOnClickListener {
                onIconClickListener.invoke()
            }
        }
    }

    inner class PhotoViewHolder(private val viewBinding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(photo: Photo) {
            Glide.with(viewBinding.root)
                .load(photo.url)
                .into(viewBinding.imgPhoto)

            viewBinding.imgPhoto.setOnClickListener {
                onPhotoClickListener.invoke(photo)
            }
        }
    }
}