package com.example.puppyfriend_frontend.View.Sns.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.View.Sns.model.Photo
import com.example.puppyfriend_frontend.databinding.ItemPhotoBinding

class PhotosAdapter(private val photosList: MutableList<Photo>,
                    private val onIconClickListener: () -> Unit,
                    private val onPhotoClickListener: (Photo) -> Unit
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
            is IconViewHolder -> holder.bind()
            is PhotoViewHolder -> holder.bind(photosList[position - 1])
        }
    }

    override fun getItemCount(): Int {
        return photosList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_ICON else VIEW_TYPE_PHOTO
    }

    inner class IconViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            itemView.setOnClickListener {
                onIconClickListener.invoke()
            }
        }
    }

    inner class PhotoViewHolder(private val viewBinding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(photo: Photo) {

            val crossFadeFactory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
            Glide.with(viewBinding.root)
                .load(photo.url)
                .fitCenter()
                .encodeQuality(0)
                .thumbnail(0.1f)
                .dontAnimate()
                .placeholder(R.drawable.img_gray_rect) // 로딩 중에 보여줄 이미지
                .transition(DrawableTransitionOptions.withCrossFade(crossFadeFactory))
                .into(viewBinding.imgPhoto)

            viewBinding.imgPhoto.setOnClickListener {
                onPhotoClickListener(photo) // 클릭 시 리스너 호출
            }

            Log.d("PhotoInfo", "Photo URL: ${photo.url}")
        }


    }
}
