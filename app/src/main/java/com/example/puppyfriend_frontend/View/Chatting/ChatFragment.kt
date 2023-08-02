package com.example.puppyfriend_frontend.View.Chatting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.databinding.FragmentChatBinding

class ChatFragment : Fragment() {
    lateinit var viewBinding: FragmentChatBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentChatBinding.inflate(inflater, container, false)

        val swipeHelper = SwipeHelper()
        val itemTouchHelper = ItemTouchHelper(swipeHelper)
        itemTouchHelper.attachToRecyclerView(viewBinding.recyclerviewChatProfile)

        return viewBinding.root
    }

}