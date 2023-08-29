package com.example.puppyfriend_frontend.View.Sns

import android.app.Dialog
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.View.Sns.adapter.PostingAdapter
import com.example.puppyfriend_frontend.View.Sns.adapter.StoryAdapter
import com.example.puppyfriend_frontend.View.Sns.model.Posting
import com.example.puppyfriend_frontend.View.Sns.model.Story
import com.example.puppyfriend_frontend.databinding.DialogPostingBigImgBinding
import com.example.puppyfriend_frontend.databinding.FragmentSnsBinding
import com.example.puppyfriend_frontend.databinding.ListitemDeletePopupBinding

class SnsFragment : Fragment(R.layout.fragment_sns) {
    private lateinit var binding: FragmentSnsBinding
    private lateinit var dialogBinding: ListitemDeletePopupBinding
    private lateinit var postingAdapter: PostingAdapter
    private lateinit var toggleHiddenFragment: ToggleHiddenFragment

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSnsBinding.bind(view)

        setupRecyclerView()

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.sns_story_width) // 간격 값을 리소스로부터 가져옴
        binding.recyclerViewStory.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                super.getItemOffsets(outRect, view, parent, state)

                // 첫 번째 아이템이 아닌 경우 상하좌우 간격을 설정
                outRect.right = spacingInPixels
            }
        })

        binding.fragmentContainer.bringToFront()
        // 이미지를 배경에 맞게 자른다.(게시글 이미지 둥근선 구현)
        binding.imgSnsPost.clipToOutline = true

        binding.imgSnsPost.setOnClickListener {
            postingImageZoom1()
        }
        // 토글버튼 클릭시 유지
        binding.togglebtnSnsTriangle.setOnClickListener{
            binding.togglebtnSnsTriangle.isSelected = binding.togglebtnSnsTriangle.isSelected != true
        }

        // toggle_hidden_view
        // Fragment를 동적으로 추가
        toggleHiddenFragment = ToggleHiddenFragment()
        childFragmentManager.beginTransaction()
            .add(R.id.fragment_container, toggleHiddenFragment)
            .commit()

        // 토클 클릭에 view hidden
        binding.togglebtnSnsTriangle.setOnCheckedChangeListener { _, isChecked ->
            val toggleFragmentView = toggleHiddenFragment.view
            val visibility = if (isChecked) View.VISIBLE else View.GONE

            toggleFragmentView?.apply {
                findViewById<View>(R.id.recyclerView_character).visibility = visibility
                findViewById<View>(R.id.text_character).visibility = visibility
                findViewById<View>(R.id.recyclerView_activity).visibility = visibility
                findViewById<View>(R.id.text_activity).visibility = visibility
            }
            binding.fragmentContainer.visibility = visibility
        }

        clickToCreatePost()
    }

    private fun setupRecyclerView() {
        val storyList = createStoryList()
        val postingList = createPostingList()

        val storyRecyclerView = binding.recyclerViewStory
        val postingRecyclerView = binding.recyclerViewPostingList

        storyRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        storyRecyclerView.adapter = StoryAdapter(storyList, requireActivity().supportFragmentManager )

        // adapter 초기화
        val layoutManager = GridLayoutManager(requireContext(), 2)
        postingRecyclerView.layoutManager = layoutManager

        postingAdapter = PostingAdapter(
            requireContext(),
            postingList,
            layoutManager,
        ) {position ->
            showDeleteDialog(position)  // (->) 반환 타입을 명시적으로 지정
        }
        postingRecyclerView.adapter = postingAdapter
    }

    private fun createStoryList(): List<Story> {
        val storyList = mutableListOf<Story>()
        storyList.add(Story(R.drawable.img_sns_post, "만두"))
        storyList.add(Story(R.drawable.img_dog_2, "댕이"))
        storyList.add(Story(R.drawable.img_sns_post, "만두"))
        storyList.add(Story(R.drawable.img_dog_2, "댕이"))
        // ... 추가적인 Character를 만들고 리스트에 추가하십시오
        return storyList
    }

    private fun createPostingList(): MutableList<Posting> {
        val postingList = mutableListOf<Posting>()
        postingList.add(Posting("8월 14일",R.drawable.kakao,"오늘은 왜이리 밥을 안먹냐", Color.parseColor("#D3F5FF")))
        postingList.add(Posting("8월 14일",R.drawable.img_sns_post,"배고파", Color.parseColor("#FCF9D0")))
        postingList.add(Posting("8월 14일",R.drawable.img_sns_post,"슬프다", Color.parseColor("#E4F9EB")))
        postingList.add(Posting("8월 14일",R.drawable.img_sns_post,"고마워", Color.parseColor("#FBE0E4")))

        return postingList
    }

    private fun showDeleteDialog(position: Int) {
        val deleteDialog = Dialog(requireContext())
        dialogBinding = ListitemDeletePopupBinding.inflate(layoutInflater)
        deleteDialog.setContentView(dialogBinding.root)

        // 클릭한 아이템의 위치를 기반으로 다이얼로그를 표시
        val postingRecyclerView = binding.recyclerViewPostingList
        val itemView = postingRecyclerView.findViewHolderForAdapterPosition(position)?.itemView
        if (itemView != null) {
            val location = IntArray(2)
            itemView.getLocationInWindow(location)
            val x = location[0]
            val y = location[1]
            deleteDialog.window?.setGravity(Gravity.TOP or Gravity.START)
            deleteDialog.window?.attributes = deleteDialog.window?.attributes?.apply {
                this.x = x + 58
                this.y = y - 58
            }
        }

        deleteDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        deleteDialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

        deleteDialog.setCanceledOnTouchOutside(true)
        deleteDialog.setCancelable(true)

        // 'btn_delete' 버튼 클릭 리스너 설정
        dialogBinding.btnDelete.setOnClickListener {
            // 클릭한 아이템에 대한 작업 수행
            val deletedPosting = postingAdapter.getPostingList()[position]
            postingAdapter.removePosting(position)

            deleteDialog.dismiss()
        }

        // 'btn_select_delete' 버튼 클릭 리스너 설정
        dialogBinding.btnSelectDelete.setOnClickListener {
            // 선택 모드 작업 수행
            deleteDialog.dismiss()

            binding.btnSelectDeleteSolo.visibility = View.VISIBLE
            postingAdapter.toggleSelectMode()
            postingAdapter.notifyDataSetChanged()

            onSoloDeleteClicked()
        }

        deleteDialog.show()
    }

    private fun onSoloDeleteClicked() {
        binding.btnSelectDeleteSolo.setOnClickListener {
            val postingList = postingAdapter.getPostingList()

            val selectedItems = mutableListOf<Posting>()
            for (posting in postingList) {
                if (posting.isChecked) {
                    selectedItems.add(posting)
                }
            }

            postingList.removeAll(selectedItems)
            postingAdapter.notifyDataSetChanged()
        }


    }



    private fun clickToCreatePost() {
        binding.btnSnsPosting.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(binding.fragmentContainer1.id, CreatePostFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun postingImageZoom1() {
        val profileViewBinding = DialogPostingBigImgBinding.inflate(layoutInflater)
        val profile = AlertDialog.Builder(requireContext()).create()
        profile.setView(profileViewBinding.root)

        profileViewBinding.imgSnsPost.clipToOutline = true

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
