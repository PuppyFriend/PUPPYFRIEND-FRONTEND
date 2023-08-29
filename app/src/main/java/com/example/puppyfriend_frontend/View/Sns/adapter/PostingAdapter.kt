package com.example.puppyfriend_frontend.View.Sns.adapter

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyfriend_frontend.View.Sns.model.Posting
import com.example.puppyfriend_frontend.databinding.DialogPostingSmallImgBinding
import com.example.puppyfriend_frontend.databinding.ItemPostingBinding

class PostingAdapter(private val context: Context,
                     private val postingList: MutableList<Posting>,
                     private val layoutManager: GridLayoutManager,
                     private val itemClickListener: (position: Int) -> Unit
)
: RecyclerView.Adapter<PostingAdapter.PostingViewHolder>() {
    private val  selectedDeleteItems = HashSet<Posting>()
    private var isSelectMode = false
    private var isClick = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostingViewHolder {
        val itemBinding = ItemPostingBinding.inflate(LayoutInflater.from(parent.context),parent, false)

        itemBinding.imgBlueMemo.clipToOutline = true

        val spanCount = layoutManager.spanCount
        return PostingViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PostingViewHolder, position: Int) {
        val posting = postingList[position]
        holder.bind(posting)

        holder.itemView.setOnLongClickListener {
            itemClickListener.invoke(position)
            isClick = true
            true
        }
    }

    override fun getItemCount(): Int {
        return postingList.size
    }

//    private fun showCustomPopup(context: Context, anchorView: View, posting: Posting) {
//        val deleteDialog = Dialog(context)
//        dialogBinding = ListitemDeletePopupBinding.inflate(LayoutInflater.from(context))
//        deleteDialog.setContentView(dialogBinding.root)
//
//        val postingRecyclerView = binding.recyclerViewPostingList
//        val position = postingRecyclerView.getChildAdapterPosition(anchorView)
//        val location = IntArray(2)
//        anchorView.getLocationInWindow(location)
//        val x = location[0]
//        val y = location[1]
//        deleteDialog.window?.setGravity(Gravity.TOP or Gravity.START)
//        deleteDialog.window?.attributes = deleteDialog.window?.attributes?.apply {
//            this.x = x
//            this.y = y
//        }
//
//        deleteDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        deleteDialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
//
//        deleteDialog.show()
//
//        val deleteButton = dialogBinding.btnDelete
//        val selectDeleteButton = dialogBinding.btnSelectDelete
//
//        deleteButton.setOnClickListener {
//            val position = postingList.indexOf(posting)
//            if (position != -1) {
//                postingList.removeAt(position)
//                notifyItemRemoved(position)
//            }
//
//            deleteDialog.dismiss()
//        }
//
//        selectDeleteButton.setOnClickListener {
//            isSelectMode = !isSelectMode
//            deleteDialog.dismiss()
//            notifyDataSetChanged() // Update the UI
//
//            selectDeleteListener.onSoloDeleteClicked()
//        }
//
//        deleteDialog.setCanceledOnTouchOutside(true)
//        deleteDialog.setCancelable(true)
//    }


    fun getPostingList(): MutableList<Posting> {
        return postingList
    }

    fun removePosting(position: Int) {
        postingList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun toggleSelectMode() {
        isSelectMode = !isSelectMode
    }

    inner class PostingViewHolder(private val itemBinding: ItemPostingBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(posting: Posting) {
            // Posting 객체로부터 데이터를 가져와서 뷰에 설정
            itemBinding.textBlueMemoDate.text = posting.date     // local date를 string으로 변환
            itemBinding.textBlueMemoComment.text = posting.content
            itemBinding.imgBlueMemo.setImageResource(posting.image)

            val backgroundColor = ColorDrawable(posting.contentBackgroundColor)
            itemBinding.viewBlueMemo.background = backgroundColor

            itemBinding.imgBlueMemo.setOnClickListener {
                postingImageZoom2(context)
            }

            if (isSelectMode) {
                itemBinding.viewSelectDeleteCircleBtn.visibility = View.VISIBLE
            } else {
                itemBinding.viewSelectDeleteCircleBtn.visibility = View.GONE
            }

            if (postingList.contains(posting)) {
                itemBinding.viewSelectDeleteCheck.visibility = if (posting.isChecked) View.VISIBLE else View.GONE
            } else {
                itemBinding.viewSelectDeleteCheck.visibility = View.GONE
            }

            itemBinding.viewSelectDeleteCircleBtn.setOnClickListener {
                if (posting.isChecked) {
                    itemBinding.viewSelectDeleteCheck.visibility = View.GONE
                    posting.isChecked = false
                    selectedDeleteItems.remove(posting)
                } else {
                    itemBinding.viewSelectDeleteCheck.visibility = View.VISIBLE
                    posting.isChecked = true
                    selectedDeleteItems.add(posting)
                }
                notifyDataSetChanged()
            }
        }

        private fun postingImageZoom2(context: Context) {
            val profileViewBinding = DialogPostingSmallImgBinding.inflate(LayoutInflater.from(context))
            val profile = AlertDialog.Builder(context).create()
            profile.setView(profileViewBinding.root)

            profileViewBinding.imgBlueMemo.clipToOutline = true

            // 다이얼로그 배경 투명 처리
            profile.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            // 다이얼로그 크기 및 위치 설정
            val layoutParams = WindowManager.LayoutParams().apply {
                copyFrom(profile.window?.attributes)
                width = WindowManager.LayoutParams.MATCH_PARENT
                height = WindowManager.LayoutParams.WRAP_CONTENT
                gravity = Gravity.CENTER
            }
            profile.window?.attributes = layoutParams

            profile.show()

            profile.setCanceledOnTouchOutside(true)
            profile.setCancelable(true)
        }

    }

}