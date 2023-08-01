package com.example.puppyfriend_frontend.View.Sns

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.puppyfriend_frontend.View.Sns.adapter.ActivityAdapter
import com.example.puppyfriend_frontend.View.Sns.adapter.CharacterAdapter
import com.example.puppyfriend_frontend.View.Sns.model.Activity
import com.example.puppyfriend_frontend.View.Sns.model.Character
import com.example.puppyfriend_frontend.databinding.FragmentToggleHiddenBinding

class ToggleHiddenFragment: Fragment() {

    private lateinit var viewBinding: FragmentToggleHiddenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentToggleHiddenBinding.inflate(inflater, container, false)

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val characterList = createCharacterList()
        val activityList = createActivityList()

        val characterRecyclerView = viewBinding.recyclerViewCharacter
        val activityRecyclerView = viewBinding.recyclerViewActivity

        characterRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        characterRecyclerView.adapter = CharacterAdapter(characterList)

        activityRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        activityRecyclerView.adapter = ActivityAdapter(activityList)
    }

    private fun createCharacterList(): List<Character> {
        val characterList = mutableListOf<Character>()
        characterList.add(Character("# 소심해요"))
        characterList.add(Character("# 낯가려요"))
        characterList.add(Character("# 활발해요"))
        // ... 추가적인 Character를 만들고 리스트에 추가하십시오
        return characterList
    }

    private fun createActivityList(): List<Activity> {
        val activityList = mutableListOf<Activity>()
        activityList.add(Activity("산책친구에요"))
        activityList.add(Activity("돌보미 구해요"))
        activityList.add(Activity("돌볼 수 있어요"))

        return activityList
    }



}