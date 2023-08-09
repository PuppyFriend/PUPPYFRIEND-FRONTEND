package com.example.puppyfriend_frontend.View.FirstLogin

import android.app.Activity
import android.content.Intent
import android.os.Build
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
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.get
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.databinding.ActivityInfoBinding
import java.time.LocalDate
import java.util.Date
import kotlin.math.roundToInt

class InfoActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityInfoBinding

    companion object {
        private const val INFO_ACTIVITY_REQUEST_CODE = 123
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        processList()
        postData()

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

    private var selectedMonthText: String? = null
    private var selectedDayText: String? = null
    private var selectedGenderText: String? = null
    private var selectedGoalText: String? = null

    private fun setupSpinner(
        spinner: Spinner,
        itemList: List<String>,
        hint: String,
        layoutResId: Int,
        targetVariable: String
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
                when (targetVariable) {
                    "month" -> selectedMonthText = parent.getItemAtPosition(position).toString()
                    "day" -> selectedDayText = parent.getItemAtPosition(position).toString()
                    "gender" -> selectedGenderText = parent.getItemAtPosition(position).toString()
                    "goal" -> selectedGoalText = parent.getItemAtPosition(position).toString()
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.d("MyTag", "onNothingSelected")
            }
        }
    }
    private fun anotherFunction() {
        Log.d("MyTag", "Selected Month: $selectedMonthText")
        Log.d("MyTag", "Selected Day: $selectedDayText")
        Log.d("MyTag", "Selected Gender: $selectedGenderText")
        Log.d("MyTag", "Selected Goal: $selectedGoalText")
    }

    private fun processList() {
        // 월 리스트 처리
        val monthList = mutableListOf<String>()
        for (i in 1..12) {
            val month = String.format("%02d", i)
            monthList.add(month)
        }
        setupSpinner(viewBinding.spinnerMonth, monthList, "월", R.layout.item_spinner, "month")

        // 일 리스트 처리
        val dayList = mutableListOf<String>()
        for (i in 1..31) {
            val day = String.format("%02d", i)
            dayList.add(day)
        }
        setupSpinner(viewBinding.spinnerDay, dayList, "일", R.layout.item_spinner, "day")

        // 성별 리스트 처리
        val genderList = mutableListOf<String>("여","남")
        setupSpinner(viewBinding.spinnerGender, genderList, "성별", R.layout.item_spinner, "gender")

        // 목표 리스트 처리
        val goalList = mutableListOf<String>()
        for (i in 1..7) {
            goalList.add("주 ${i}회")
        }
        setupSpinner(viewBinding.spinnerGoal, goalList, "주 N회", R.layout.item_spinner_goal, "goal")
        anotherFunction()


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun postData() {
        viewBinding.btnArrow.setOnClickListener {
            val intent = Intent(this, MoreInfoActivity::class.java)
            val puppyName = viewBinding.editPuppyName.text.toString()
            val puppySpecies = viewBinding.editPuppySpecies.text.toString()

            val puppyYear = viewBinding.editYear.text.toString()
            val puppyAge = (LocalDate.now().year - puppyYear.toInt()).toString()

            val puppyMonth = (selectedMonthText?.toInt()?.plus(puppyAge.toInt() * 12)).toString()

            val puppyGender = selectedGenderText


            val puppyGoal = selectedGoalText!!.filter { it.isDigit() }

            intent.putExtra("puppy_name", puppyName)
            intent.putExtra("puppy_species", puppySpecies)
            intent.putExtra("puppy_age", puppyAge)
            intent.putExtra("puppy_month", puppyMonth)
            intent.putExtra("puppy_gender", puppyGender)
            intent.putExtra("puppy_goal", puppyGoal)
            startActivity(intent)
        }

    }


}

