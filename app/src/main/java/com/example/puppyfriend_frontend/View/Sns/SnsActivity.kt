package com.example.puppyfriend_frontend.View.Sns

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.View.Home.HomeActivity
import com.example.puppyfriend_frontend.View.Sns.adapter.PostingAdapter
import com.example.puppyfriend_frontend.View.Sns.model.Posting
import com.example.puppyfriend_frontend.databinding.ActivitySnsBinding
import java.time.LocalDate

class SnsActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActivitySnsBinding
    private lateinit var toggleHiddenFragment: ToggleHiddenFragment

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySnsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // 이미지를 배경에 맞게 자른다.(게시글 이미지 둥근선 구현)
        viewBinding.imgSnsPost.clipToOutline = true

        // 토글버튼 클릭시 유지
        viewBinding.togglebtnSnsTriangle.setOnClickListener{
            viewBinding.togglebtnSnsTriangle.isSelected = viewBinding.togglebtnSnsTriangle.isSelected != true
        }

        // toggle_hidden_view
        // Fragment를 동적으로 추가
        toggleHiddenFragment = ToggleHiddenFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, toggleHiddenFragment)
            .commit()

        // 토클 클릭에 view hidden
        viewBinding.togglebtnSnsTriangle.setOnCheckedChangeListener { _, isChecked ->
            val toggleFragmentView = toggleHiddenFragment.view
            val visibility = if (isChecked) View.VISIBLE else View.GONE

            toggleFragmentView?.apply {
                findViewById<View>(R.id.recyclerView_character).visibility = visibility
                findViewById<View>(R.id.text_character).visibility = visibility
                findViewById<View>(R.id.recyclerView_activity).visibility = visibility
                findViewById<View>(R.id.text_activity).visibility = visibility
                findViewById<View>(R.id.view_toggle_hidden).visibility = visibility

            }
                viewBinding.fragmentContainer.visibility = visibility
        }

        // recyclerView 설정
        setupRecyclerView()
        clickToCreatePost()
    }

    // recylcerView에 데이터 넣기
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupRecyclerView() {
        val postingList = createPostingList()

        val postingRecyclerView = viewBinding.recyclerViewPostingList

        postingRecyclerView.layoutManager = GridLayoutManager(this, 2)
        postingRecyclerView.adapter = PostingAdapter(postingList)

    }

    @RequiresApi(Build.VERSION_CODES.O)                 // api 레벨 26이상 지원
    private fun createPostingList(): List<Posting> {
        val postingList = mutableListOf<Posting>()

        val localDate = LocalDate.now()
        var date = "${localDate.monthValue}월  ${localDate.dayOfMonth}일 "       // 날짜 형식 설정

        postingList.add(Posting(date, R.drawable.img_sns_post,"오늘은 왜이리 밥을 안먹냐..", Color.parseColor("#D3F5FF")))
        postingList.add(Posting(date, R.drawable.img_sns_post,"낼 병원가서 검진 좀 받아야겠다", Color.parseColor("#E4F9EB")))
        postingList.add(Posting(date, R.drawable.img_sns_post,"오늘은 왜이리 밥을 안먹냐..", Color.parseColor("#FFDCCE")))

        // ... 추가적인 Character를 만들고 리스트에 추가하십시오
        return postingList
    }

    // 게시글 버튼 클릭시 createPost 페이지로 이동 함수
    private fun clickToCreatePost() {
        viewBinding.btnSnsPosting.setOnClickListener {
            val intent = Intent(this, CreatePostActivity::class.java)
            startActivity(intent)
        }
    }
}