package com.example.puppyfriend_frontend.View.Home

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.lang.UCharacter
import android.os.Bundle
import android.view.*
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.GridLayout
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.View.FirstLogin.InfoActivity
import com.example.puppyfriend_frontend.databinding.CustomDialogBinding
import com.example.puppyfriend_frontend.databinding.FragmentHomeBinding
import com.example.puppyfriend_frontend.databinding.HomeProfileZoomBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class HomeFragment : Fragment() {

    private lateinit var viewBinding: FragmentHomeBinding

    private var isZoomed = false             // 홈 프로필 사진 클릭 boolean 값
    private var showDialogFlag: Boolean = true
    private var isCalendarVisible = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.togglebtnHomeTriangle.setOnClickListener {
            isCalendarVisible = !isCalendarVisible
            toggleCalendarVisibility(isCalendarVisible)
            viewBinding.togglebtnHomeTriangle.isSelected =
                viewBinding.togglebtnHomeTriangle.isSelected != true
//            viewBinding.gridlayoutReview2.visibility = if (isCalendarVisible) View.VISIBLE else View.GONE
        }

        viewBinding.imgDogProfile.clipToOutline = true

        viewBinding.progressbarFront.progress = 86

        viewBinding.progressbarFront.rotation = 90f     // 회전
        viewBinding.progressbarFront.scaleY = -1f       // y축을 기준으로 좌우 반전

        // 현재 month 확인
        fun String.getTimeNow(): String {
            return try {
                val date = Date(System.currentTimeMillis())
                val simpleDateFormat = SimpleDateFormat(this)
                simpleDateFormat.format(date)
            } catch (e: Exception) {
                UCharacter.GraphemeClusterBreak.L.equals(e.message)
                ""
            }
        }

        var nowDate: String = "M".getTimeNow()
        var nowDay: String = "D".getTimeNow()

        // 현재 주차 확인
        fun getCurrentWeekOfMonth(): Int {
            val calendar = Calendar.getInstance()
            calendar.firstDayOfWeek = Calendar.MONDAY // 한 주의 시작을 월요일로 설정
            calendar.set(Calendar.DAY_OF_MONTH, nowDay.toInt()) // 현재 월의 일로 설정

            val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            val currentWeek = calendar.get(Calendar.WEEK_OF_MONTH)

            while (calendar.get(Calendar.DAY_OF_MONTH) < currentDay) {
                calendar.add(Calendar.DAY_OF_MONTH, 1)
            }

            return currentWeek
        }

        var currentWeeOfMonth: String = getCurrentWeekOfMonth().toString()

        // 함께한 퍼프 친구 이름
        var puppyFriendName: String = "루루, 용식"
        val maxLength = 10

        if (puppyFriendName.length > maxLength) {
            puppyFriendName = puppyFriendName.substring(0, maxLength)
        }


        viewBinding.textReviewInfo.text =
            "${nowDate}월 ${currentWeeOfMonth}주차에는 총 n번의 산책을 했어요.\n함께한 퍼프친구 : $puppyFriendName"

//        getData()

        viewBinding.imgDogProfile.setOnClickListener {
            homeProfileZoom()
        }

        walkingReview()

//        if (showDialogFlag) {
//            showDialog()
//        }
    }

    private fun showDialog(){
        val dialogViewBinding = CustomDialogBinding.inflate(layoutInflater) // customa_dialog.xml 레이아웃 사용
        val dialog= androidx.appcompat.app.AlertDialog.Builder(requireContext()).create()
        dialog.setView(dialogViewBinding.root)


        // dialog 배경 투명 처리
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 다이얼로그 크기 설정
        dialog.window?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)    // 다이얼로그 영역 밖 클릭 시, 다이얼 로그 삭제 금지
        dialog.setCancelable(true)                 // 취소가 가능하도록 하는 코드


        dialogViewBinding.btnStartInfo.setOnClickListener {
            // 버튼 클릭 시 처리할 로직 작성
            val intent = Intent(requireActivity(), InfoActivity::class.java)
            startActivity(intent)
        }

        dialog.show()
    }

    private fun homeProfileZoom() {
        val profileViewBinding = HomeProfileZoomBinding.inflate(layoutInflater)
        val profile = AlertDialog.Builder(requireContext()).create()
        profile.setView(profileViewBinding.root)

        profileViewBinding.imgDogProfile.clipToOutline = true

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



    @SuppressLint("SetTextI18n")
    private fun getData() {
        val puppyName = "댕댕"
        val puppySpecies = "골든리트리버"
        val puppyAge = "2"
        val puppyMonth = "26"
        val puppyGender = "여"
        val puppyGoal = 7

        viewBinding.textName.text = " &$puppyName"
        viewBinding.textInfo.text = "$puppySpecies - ${puppyAge}세(${puppyMonth}개월) - ${puppyGender}아"

        val puppyGoalPercent = ((6.0 / puppyGoal!!.toDouble()) * 100 ).roundToInt().toString()

        viewBinding.textProgessbarPercent.text = "$puppyGoalPercent%"
        viewBinding.progressbarFront.progress = puppyGoalPercent!!.toInt()
        viewBinding.textProgessbarGoal.text = "목표 ${puppyGoal}회 중 6회 달성!\n아주 잘하고 있어요. 댕댕이와 함께 행복한 일주일이네요!"
    }


    private fun openCalendar() {
        viewBinding.togglebtnHomeTriangle.setOnClickListener {
            if(viewBinding.togglebtnHomeTriangle.isChecked) {
                viewBinding.togglebtnHomeTriangle.animate()
                    .alpha(0.0f)
                    .duration = 1000
                viewBinding.togglebtnHomeTriangle.animate().withEndAction{
                    viewBinding.togglebtnHomeTriangle.visibility = View.GONE
                    viewBinding.togglebtnHomeTriangle.alpha = 1.0f              // 애니메이션이 끝난 후 투명도 리셀
                }
            } else {
                viewBinding.togglebtnHomeTriangle.visibility = View.VISIBLE
                viewBinding.togglebtnHomeTriangle.animate()
                    .alpha(1.0f)
                    .duration = 1000
            }

        }
    }


    private fun walkingReview() {
        val cellSizeInPx = dpToPx(38)
        val horizontalGap = dpToPx(7)
        val verticalGap = dpToPx(8)

        viewBinding.gridlayoutReview1.apply {
            val daysOfWeek = resources.getStringArray(R.array.days_of_week) // string-array 가져오기

            for ((index, day) in daysOfWeek.withIndex()) {
                val imageView = ImageView(requireContext()).apply {
                    id = View.generateViewId()

                    val cellLayoutParams = GridLayout.LayoutParams().apply {
                        width = cellSizeInPx
                        height = cellSizeInPx
                        rowSpec = GridLayout.spec(0) // 행은 항상 0으로 고정
                        columnSpec = GridLayout.spec(index)

                        if (index > 0) {
                            leftMargin = horizontalGap
                        }
                    }

                    layoutParams = cellLayoutParams

                    // 초기 이미지 설정 (클릭되지 않은 상태)
                    setImageResource(R.drawable.img_home_review_unclicked)

                    // 클릭 시 이미지 변경
                    setOnClickListener {
                        if (isSelected) {
                            setImageResource(R.drawable.img_home_review_unclicked)
                        } else {
                            setImageResource(R.drawable.img_home_review_clicked)
                        }

                        isSelected = !isSelected
                    }
                }
                addView(imageView)

//                val textView = TextView(this@HomeActivity).apply {
//                    id = View.generateViewId()
//
//                    val cellLayoutParams = GridLayout.LayoutParams().apply {
//                        width = cellSizeInPx
//                        height = cellSizeInPx
//                        rowSpec = GridLayout.spec(0) // 행은 항상 0으로 고정
//                        columnSpec = GridLayout.spec(index)
//
//                        if (index > 0) {
//                            leftMargin = horizontalGap
//                        }
//                    }
//
//                    layoutParams = cellLayoutParams
//                    // text의 속성 추가하기
//                    gravity = Gravity.CENTER
//                    textSize = 10f // 텍스트 크기를 직접 설정하거나 dimen 리소스를 사용하세요
//                    text = day
//
//                    setTextColor(Color.parseColor("#565656"))
//
//                    // 폰트 설정
//                    val typeface = ResourcesCompat.getFont(this@HomeActivity, R.font.inter_semibold)
//                    setTypeface(typeface)
//                    setTypeface(typeface, Typeface.BOLD)
//
//                }
//                addView(textView)
            }
        }

        viewBinding.gridlayoutReview2.apply {

            for (row in 0 until 3) {
                for (col in 0 until 7) {
                    val cell = ImageView(requireContext()).apply {
                        id = View.generateViewId()

                        val cellLayoutParams = GridLayout.LayoutParams().apply {
                            width = cellSizeInPx
                            height = cellSizeInPx
                            rowSpec = GridLayout.spec(row)
                            columnSpec = GridLayout.spec(col)
                        }

                        // 간격 조절
                        if (col > 0) {
                            cellLayoutParams.leftMargin = horizontalGap
                        }
                        if (row > 0) {
                            cellLayoutParams.topMargin = verticalGap
                        }

                        layoutParams = cellLayoutParams

                        // 초기 이미지 설정 (클릭되지 않은 상태)
                        setImageResource(R.drawable.img_home_review_unclicked)
                        setOnClickListener {
                            // 클릭 시 이미지 변경
                            if (isSelected) {
                                setImageResource(R.drawable.img_home_review_unclicked)
                            } else {
                                setImageResource(R.drawable.img_home_review_clicked)
                            }
                            isSelected = !isSelected
                        }
                    }
                    addView(cell)
                }
            }


            val layoutParams = layoutParams as ConstraintLayout.LayoutParams
            layoutParams.horizontalChainStyle = ConstraintLayout.LayoutParams.CHAIN_SPREAD_INSIDE
            layoutParams.verticalChainStyle = ConstraintLayout.LayoutParams.CHAIN_SPREAD_INSIDE
        }
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

    private fun toggleCalendarVisibility(show: Boolean) {
        val targetHeightOther: Int =
            if (show) resources.getDimensionPixelSize(R.dimen.other_height) else 0
        val targetHeightCalendar: Int =
            if (show) resources.getDimensionPixelSize(R.dimen.calendar_height) else 0

        val anim: Animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                val translationYOther = targetHeightOther.toFloat() * interpolatedTime
                val translationYCalendar = targetHeightCalendar.toFloat() * interpolatedTime

                with(viewBinding) {
                    textGoal.translationY = translationYOther
                    viewProgressbarOutline.translationY = translationYOther
                    progressbarFront.translationY = translationYOther
                    progressbarBack.translationY = translationYOther
                    textProgessbarPercent.translationY = translationYOther
                    textProgessbarGoal.translationY = translationYOther

                    gridlayoutReview1.translationY = translationYCalendar
                    gridlayoutReview2.translationY = translationYCalendar
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        anim.duration = 1000
        viewBinding.gridlayoutReview2.startAnimation(anim)

        // 캘린더 fade in/out
        if (show) {
            viewBinding.gridlayoutReview2.alpha = 0f
            viewBinding.gridlayoutReview2.visibility = View.VISIBLE
            viewBinding.gridlayoutReview2.animate()
                .alpha(1f)
                .setDuration(500)
                .start()
        } else {
            viewBinding.gridlayoutReview2.animate()
                .alpha(0f)
                .setDuration(0)
                .withEndAction {
                    viewBinding.gridlayoutReview2.visibility = View.GONE
                }
                .start()
        }
    }

}