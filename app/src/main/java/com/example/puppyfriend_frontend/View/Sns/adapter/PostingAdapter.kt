package com.example.puppyfriend_frontend.View.Sns.adapter

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.View.Sns.model.Posting
import com.example.puppyfriend_frontend.databinding.DialogPostingSmallImgBinding
import com.example.puppyfriend_frontend.databinding.ItemPostingBinding

interface SelectDeleteListener {
    fun onSoloDeleteClicked()
}
class PostingAdapter(private val context: Context,
                     private val postingList: MutableList<Posting>,
                     private val  layoutManager: GridLayoutManager,
                        private val selectDeleteListener: SelectDeleteListener)
: RecyclerView.Adapter<PostingAdapter.PostingViewHolder>() {
    private val  selectedDeleteItems = HashSet<Posting>()
    private var isSelectMode = false
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
            showCustomPopup(holder.itemView.context, it, posting)
            true
        }
    }

    override fun getItemCount(): Int {
        return postingList.size
    }

    private fun showCustomPopup(context: Context, anchorView: View, posting: Posting) {
        val dialogBuilder = AlertDialog.Builder(context)
        val dialogView = LayoutInflater.from(context).inflate(R.layout.listitem_delete_popup, null)

        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()

        alertDialog.setCanceledOnTouchOutside(true)    // 다이얼로그 영역 밖 클릭 시, 다이얼 삭제
        alertDialog.setCancelable(true)                 // 취소가 가능하도록 하는 코드

        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

        val deleteButton = dialogView.findViewById<Button>(R.id.btn_delete)
        deleteButton.setOnClickListener {
            val position = postingList.indexOf(posting)
            if (position != -1) {
                postingList.removeAt(position)
                notifyItemRemoved(position)
            }

            alertDialog.dismiss()
        }
        val selectDeleteButton = dialogView.findViewById<Button>(R.id.btn_select_delete)
        selectDeleteButton.setOnClickListener {
            isSelectMode = !isSelectMode
            alertDialog.dismiss()
            notifyDataSetChanged() // Update the UI

            selectDeleteListener.onSoloDeleteClicked()

        }

        // 팝업 다이얼로그의 위치를 anchorView 아래에 띄우도록 설정합니다.
        val location = IntArray(2)
        // 다이얼로그의 위치를 클릭한 아이템 바로 위에 표시
        val marginLeft = context.resources.getDimensionPixelSize(R.dimen.dialog_offset_x)
        val marginTop = context.resources.getDimensionPixelSize(R.dimen.dialog_offset_y)
        anchorView.getLocationOnScreen(location)
        Log.d("ItemClick", "Clicked item location: (${location[0]}, ${location[1]})")
        val layoutParams = WindowManager.LayoutParams()

        layoutParams.copyFrom(alertDialog.window?.attributes)
        layoutParams.x = location[0]
        layoutParams.y = location[1] + anchorView.height

        alertDialog.window?.attributes = layoutParams

        alertDialog.show()
    }

    fun getPostingList(): MutableList<Posting> {
        return postingList
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
                itemBinding.viewSelectDeleteCheck.visibility = if (posting.isChecked) View.VISIBLE else View.GONE
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