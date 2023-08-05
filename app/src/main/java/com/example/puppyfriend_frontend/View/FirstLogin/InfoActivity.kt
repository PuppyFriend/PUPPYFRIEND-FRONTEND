package com.example.puppyfriend_frontend.View.FirstLogin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityInfoBinding

    companion object {
        private const val INFO_ACTIVITY_REQUEST_CODE = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        processList()

        viewBinding.btnArrow.setOnClickListener {
            val intent = Intent(this, MoreInfoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK)
        super.onBackPressed()
    }

    private fun dipToPixels(dipValue: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dipValue,
            resources.displayMetrics
        )
    }

    private fun setupSpinner(
        spinner: Spinner,
        itemList: List<String>,
        hint: String,
        layoutResId: Int,
    ) {
        val myAdapter = object : ArrayAdapter<String>(this, layoutResId) {

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val v = super.getView(position, convertView, parent)

                if (position == count) {
                    (v.findViewById<View>(R.id.text_item_spinner) as TextView).text = ""
                    (v.findViewById<View>(R.id.text_item_spinner) as TextView).hint = getItem(count)
                }

                return v
            }

            override fun getCount(): Int {
                return super.getCount() - 1
            }
        }

        myAdapter.addAll(itemList.toMutableList())
        myAdapter.add(hint)
        spinner.adapter = myAdapter
        spinner.setSelection(myAdapter.count)
        spinner.dropDownVerticalOffset = dipToPixels(45f).toInt()

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when (position) {
                    0 -> {

                    }
                    1 -> {

                    }
                    // ...
                    else -> {

                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.d("MyTag", "onNothingSelected")
            }
        }
    }

    private fun processList() {
        // 월 리스트 처리
        val monthList = mutableListOf<String>()
        for (i in 1..12) {
            val month = String.format("%02d", i)
            monthList.add(month)
        }
        setupSpinner(viewBinding.spinnerMonth, monthList, "월", R.layout.item_spinner)

        // 일 리스트 처리
        val dayList = mutableListOf<String>()
        for (i in 1..31) {
            val day = String.format("%02d", i)
            dayList.add(day)
        }
        setupSpinner(viewBinding.spinnerDay, dayList, "일", R.layout.item_spinner)

        // 성별 리스트 처리
        val genderList = mutableListOf<String>("여","남")
        setupSpinner(viewBinding.spinnerGender, genderList, "성별", R.layout.item_spinner)

        // 목표 리스트 처리
        val goalList = mutableListOf<String>()
        for (i in 1..7) {
            goalList.add("주 ${i}회")
        }
        setupSpinner(viewBinding.spinnerGoal, goalList, "주 N회", R.layout.item_spinner_goal)


    }
}

