package com.example.puppyfriend_frontend.View.Sns

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.View.Sns.adapter.PostingAdapter
import com.example.puppyfriend_frontend.View.Sns.model.Posting
import com.example.puppyfriend_frontend.databinding.FragmentOtherSnsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OtherSnsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OtherSnsFragment : Fragment() {
    private lateinit var viewBinding: FragmentOtherSnsBinding
    private lateinit var toggleHiddenFragment: ToggleHiddenFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewBinding = FragmentOtherSnsBinding.inflate(layoutInflater)

        viewBinding.btnFollowing.setOnClickListener{
            viewBinding.btnFollowing.isSelected = viewBinding.btnFollowing.isSelected != true
        }
        viewBinding.btnSettingXBack.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this).commit()
        }

        toggleHiddenFragment = ToggleHiddenFragment()
        childFragmentManager.beginTransaction()
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
//                findViewById<View>(R.id.view_toggle_hidden).visibility = visibility
            }
            viewBinding.fragmentContainer.visibility = visibility
        }

        setupRecyclerView()
        return viewBinding.root
    }

    private fun setupRecyclerView() {
        val postingList = createPostingList()

        val postingRecyclerView = viewBinding.recyclerViewPostingList

        postingRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        postingRecyclerView.adapter = PostingAdapter(postingList,
            postingRecyclerView.layoutManager as GridLayoutManager
        )
    }

    private fun createPostingList(): MutableList<Posting> {
        val postingList = mutableListOf<Posting>()
        postingList.add(
            Posting("8월 14일",
                R.drawable.img_sns_post,"오늘은 왜이리 밥을 안먹냐", Color.parseColor("#D3F5FF"))
        )
        postingList.add(
            Posting("8월 14일",
                R.drawable.img_sns_post,"배고파", Color.parseColor("#FCF9D0"))
        )
        postingList.add(
            Posting("8월 14일",
                R.drawable.img_sns_post,"슬프다", Color.parseColor("#E4F9EB"))
        )
        postingList.add(
            Posting("8월 14일",
                R.drawable.img_sns_post,"고마워", Color.parseColor("#FBE0E4"))
        )

        return postingList
    }

}