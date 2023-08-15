package com.example.puppyfriend_frontend.View.Sns

import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.View.Sns.adapter.PostingAdapter
import com.example.puppyfriend_frontend.View.Sns.adapter.StoryAdapter
import com.example.puppyfriend_frontend.View.Sns.model.Posting
import com.example.puppyfriend_frontend.View.Sns.model.Story
import com.example.puppyfriend_frontend.databinding.FragmentSnsBinding

class SnsFragment : Fragment(R.layout.fragment_sns) {
    private lateinit var binding: FragmentSnsBinding
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

        // 이미지를 배경에 맞게 자른다.(게시글 이미지 둥근선 구현)
        binding.imgSnsPost.clipToOutline = true

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
                findViewById<View>(R.id.view_toggle_hidden).visibility = visibility
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
        storyRecyclerView.adapter = StoryAdapter(storyList)

        postingRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        postingRecyclerView.adapter = PostingAdapter(postingList,
            postingRecyclerView.layoutManager as GridLayoutManager
        )
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
        postingList.add(Posting("8월 14일",R.drawable.img_sns_post,"오늘은 왜이리 밥을 안먹냐", Color.parseColor("#D3F5FF")))
        postingList.add(Posting("8월 14일",R.drawable.img_sns_post,"배고파", Color.parseColor("#FCF9D0")))
        postingList.add(Posting("8월 14일",R.drawable.img_sns_post,"슬프다", Color.parseColor("#E4F9EB")))
        postingList.add(Posting("8월 14일",R.drawable.img_sns_post,"고마워", Color.parseColor("#FBE0E4")))

        return postingList
    }

    private fun clickToCreatePost() {
        binding.btnSnsPosting.setOnClickListener {
            val intent = Intent(requireContext(), CreatePostActivity::class.java)
            startActivity(intent)
        }
    }
}