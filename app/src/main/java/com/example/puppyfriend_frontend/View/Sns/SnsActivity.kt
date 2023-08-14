package com.example.puppyfriend_frontend.View.Sns

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.View.Sns.adapter.PostingAdapter
import com.example.puppyfriend_frontend.View.Sns.model.Posting
import com.example.puppyfriend_frontend.databinding.ActivitySnsBinding

class SnsActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActivitySnsBinding
    private lateinit var toggleHiddenFragment: ToggleHiddenFragment

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySnsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView_posting_list)

        // Check if the data is received from the previous activity
        if (intent != null && intent.hasExtra("date") && intent.hasExtra("image") && intent.hasExtra("content") && intent.hasExtra("backgroundColor")) {
            val date = intent.getStringExtra("date")
            val image = intent.getStringExtra("image")
            val content = intent.getStringExtra("content")
            val backgroundColor = intent.getIntExtra("backgroundColor", Color.WHITE)

            // Now you have the data, you can use it to populate the RecyclerView using an adapter
            val dataList = mutableListOf<Posting>()
            dataList.add(Posting(date!!, image!!, content!!, backgroundColor))
            // Add more items to dataList if you have multiple items to display

            val adapter = PostingAdapter(dataList)
            recyclerView.layoutManager = GridLayoutManager(this, 2)
            recyclerView.adapter = adapter

            val recyclerView: RecyclerView = findViewById(R.id.recyclerView_posting_list)

            // Check if the data is received from the previous activity
            if (intent != null && intent.hasExtra("date") && intent.hasExtra("image") && intent.hasExtra("content") && intent.hasExtra("backgroundColor")) {
                val date = intent.getStringExtra("date")
                val image = intent.getStringExtra("image")
                val content = intent.getStringExtra("content")
                val contentBackgroundColor = intent.getIntExtra("backgroundColor", Color.WHITE)

                // Now you have the data, you can use it to populate the RecyclerView using an adapter
                val dataList = mutableListOf<Posting>()
                dataList.add(Posting(date!!, image!!, content!!, contentBackgroundColor))
                // Add more items to dataList if you have multiple items to display

                val adapter = PostingAdapter(dataList)
                recyclerView.layoutManager = GridLayoutManager(this, 2)
                recyclerView.adapter = adapter

                // Log the information stored in dataList
                for (posting in dataList) {
                    Log.d("SnsActivity", "Date: ${posting.date}")
                    Log.d("SnsActivity", "Image: ${posting.image}")
                    Log.d("SnsActivity", "Content: ${posting.content}")
                    Log.d("SnsActivity", "BackgroundColor: ${posting.contentBackgroundColor}")
                }
            }
        }


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

        clickToCreatePost()
    }


    private fun clickToCreatePost() {
        viewBinding.btnSnsPosting.setOnClickListener {
            val intent = Intent(this, CreatePostActivity::class.java)
            startActivity(intent)
        }
    }
}
