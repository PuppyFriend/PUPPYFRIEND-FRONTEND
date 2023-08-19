package com.example.puppyfriend_frontend.View.Sns.adapter

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.View.Sns.model.Posting
import com.example.puppyfriend_frontend.databinding.ItemPostingBinding

class PostingAdapter(private val postingList: MutableList<Posting>, private val  layoutManager: GridLayoutManager) : RecyclerView.Adapter<PostingAdapter.PostingViewHolder>() {
    private val  selectedDeleteItems = HashSet<Posting>()
    private var selected = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostingViewHolder {
        val itemBinding = ItemPostingBinding.inflate(LayoutInflater.from(parent.context),parent, false)

        itemBinding.imgBlueMemo.clipToOutline = true

        val spanCount = layoutManager.spanCount
        return PostingViewHolder(itemBinding, spanCount)
    }

    override fun onBindViewHolder(holder: PostingViewHolder, position: Int) {
        val posting = postingList[position]
        holder.bind(posting)

        holder.itemView.setOnLongClickListener {
            val location = IntArray(2)
            holder.itemView.getLocationOnScreen(location)

            val spanCount = layoutManager.spanCount // 이미 설정된 GridLayoutManager를 사용
            val spanIndex = position % spanCount
            val spanSize = (it.layoutParams as GridLayoutManager.LayoutParams).spanSize

            val x = location[0] + spanIndex * holder.itemView.width
            val y = location[1]

            showCustomPopup(holder.itemView.context, x, y, posting)

            true
        }
    }

    override fun getItemCount(): Int {
        return postingList.size
    }

    private fun showCustomPopup(context: Context, x:Int, y:Int, posting: Posting) {
        val dialogBuilder = AlertDialog.Builder(context)
        val dialogView = LayoutInflater.from(context).inflate(R.layout.listitem_delete_popup, null)

        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()

        alertDialog.setCanceledOnTouchOutside(true)    // 다이얼로그 영역 밖 클릭 시, 다이얼 삭제
        alertDialog.setCancelable(true)                 // 취소가 가능하도록 하는 코드

        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

        val marginTop = context.resources.getDimensionPixelSize(R.dimen.dialog_offset_y)
        val marginLeft = context.resources.getDimensionPixelSize(R.dimen.dialog_offset_x)
        val layoutParams = WindowManager.LayoutParams()

        layoutParams.copyFrom(alertDialog.window?.attributes)
        layoutParams.x = x - marginLeft
        layoutParams.y = y - marginTop

        alertDialog.window?.attributes = layoutParams

        // 팝업 다이얼로그 내부의 뷰들을 초기화하고 설정합니다.
        val deleteButton = dialogView.findViewById<Button>(R.id.btn_delete)
        deleteButton.setOnClickListener {
            val position = postingList.indexOf(posting) // 삭제할 아이템의 위치를 찾습니다.
            if (position != -1) {
                postingList.removeAt(position) // postingList에서 아이템을 제거합니다.(MutableList는 변경 가능한 리스트(list는 불가능))
                notifyItemRemoved(position) // 어댑터에 아이템 삭제를 알립니다.
            }

            alertDialog.dismiss() // 다이얼로그를 닫습니다.
        }


        alertDialog.show()


    }



    inner class PostingViewHolder(private val itemBinding: ItemPostingBinding, private val spanCount: Int) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(posting: Posting) {
            // Posting 객체로부터 데이터를 가져와서 뷰에 설정
            itemBinding.textBlueMemoDate.text = posting.date     // local date를 string으로 변환
            itemBinding.textBlueMemoComment.text = posting.content
            itemBinding.imgBlueMemo.setImageResource(posting.image)

            val backgroundColor = ColorDrawable(posting.contentBackgroundColor)
            itemBinding.viewBlueMemo.background = backgroundColor

            updateCircleButton(posting)

            itemBinding.viewSelectDeleteCircleBtn.setOnClickListener {
                toggleCircleSelection(posting)
            }
        }

        private fun updateCircleButton(posting: Posting) {
            val isSelected = selectedDeleteItems.contains(posting)
            itemBinding.viewSelectDeleteCircleBtn.visibility = if (selected || isSelected) View.VISIBLE else View.GONE
            itemBinding.viewSelectDeleteCheck.visibility = if (isSelected) View.VISIBLE else View.GONE
        }

        private fun toggleCircleSelection(posting: Posting) {
            if (selectedDeleteItems.contains(posting)) {
                selectedDeleteItems.remove(posting)
            } else {
                selectedDeleteItems.add(posting)
            }
            notifyDataSetChanged()
        }

    }

}