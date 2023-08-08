package com.example.puppyfriend_frontend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import com.example.puppyfriend_frontend.databinding.FragmentWalkNeighborBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WalkNeighborFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WalkNeighborFragment : Fragment() {
    private lateinit var viewBinding:FragmentWalkNeighborBinding
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = FragmentWalkNeighborBinding.inflate(layoutInflater)
        var data:MutableList<String> = loadData()
        var adaper=WalkNeighborAdapter()
        adaper.listData=data
        viewBinding.viewPager.adapter=adaper
        return viewBinding.root
    }
    fun loadData():MutableList<String>{
        val data:MutableList<String> = mutableListOf()
        for(no in 1..100){
            data.add("루피 ${no}")
        }
        return data
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WalkNeighborFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WalkNeighborFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}