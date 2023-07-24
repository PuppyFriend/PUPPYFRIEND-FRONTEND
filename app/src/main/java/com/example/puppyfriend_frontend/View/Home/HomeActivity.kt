package com.example.puppyfriend_frontend.View.Home

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import android.text.InputFilter
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyfriend_frontend.View.FirstLogin.InfoActivity
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.databinding.ActivityHomeBinding
import com.example.puppyfriend_frontend.databinding.CustomDialogBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.*
import java.util.regex.Pattern

class HomeActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityHomeBinding

    private var showDialogFlag: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        
        // 후에 산책 리뷰로 연동해야함 *
        val goalPercent: Int = 86
        viewBinding.progressbarFront.progress = goalPercent
        viewBinding.textProgessbarPercent.text = "$goalPercent%"

        viewBinding.progressbarFront.rotation = 90f     // 회전
        viewBinding.progressbarFront.scaleY = -1f       // y축을 기준으로 좌우 반전


        viewBinding.imgDog.setOnClickListener {
            val imgResId = R.drawable.img_real_dog
            clickEvent(imgResId)
        }

        // 현재 month 확인
        fun String.getTimeNow(): String {
            return try {
                val date = Date(System.currentTimeMillis())
                val simpleDateFormat = SimpleDateFormat(this)
                simpleDateFormat.format(date)
            } catch (e: Exception) {
                L.equals(e.message)
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


        viewBinding.textReviewInfo.text = "${nowDate}월 ${currentWeeOfMonth}주차에는 총 n번의 산책을 했어요.\n함께한 퍼프친구 : $puppyFriendName"


        if (showDialogFlag) {
            showDialog()
        }

    }

    private fun clickEvent(imgResId: Int) {
        val intent = Intent(this,  ImageActivity::class.java)
        intent.putExtra("imgResId", imgResId)

        // Optional
        val options = ActivityOptions.makeSceneTransitionAnimation(this, viewBinding.imgDog, "imgTrans")
        startActivity(intent, options.toBundle())
    }

    private fun showDialog(){
        val dialogViewBinding = CustomDialogBinding.inflate(layoutInflater) // customa_dialog.xml 레이아웃 사용
        val dialog= androidx.appcompat.app.AlertDialog.Builder(this).create()
        dialog.setView(dialogViewBinding.root)


        // dialog 배경 투명 처리
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 다이얼로그 크기 설정
        dialog.window?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(false)    // 다이얼로그 영역 밖 클릭 시, 다이얼 로그 삭제 금지
        dialog.setCancelable(true)                 // 취소가 가능하도록 하는 코드


        dialogViewBinding.btnStartInfo.setOnClickListener {
            // 버튼 클릭 시 처리할 로직 작성
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }

        dialog.show()
    }


}
