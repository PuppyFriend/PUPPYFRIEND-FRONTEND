package com.example.puppyfriend_frontend.View.Sns

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.View.Sns.adapter.PhotosAdapter
import com.example.puppyfriend_frontend.View.Sns.adapter.PostingAdapter
import com.example.puppyfriend_frontend.View.Sns.model.Photo
import com.example.puppyfriend_frontend.View.Sns.model.Posting
import com.example.puppyfriend_frontend.View.Sns.model.SharedViewModel
import com.example.puppyfriend_frontend.databinding.ActivityCreatepostBinding

class CreatePostActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityCreatepostBinding
    private lateinit var photosAdapter: PhotosAdapter
    private lateinit var postingAdapter: PostingAdapter
    private lateinit var viewModel: SharedViewModel
    private val posts = mutableListOf<Posting>()

    companion object {
        private const val READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 1
    }

    private val colorCheckIds = mapOf(
        R.id.view_background_color_blue to R.id.view_background_color_blue_check,
        R.id.view_background_color_green to R.id.view_background_color_green_check,
        R.id.view_background_color_orange to R.id.view_background_color_orange_check,
        R.id.view_background_color_yellow to R.id.view_background_color_yellow_check,
        R.id.view_background_color_pink to R.id.view_background_color_pink_check
    )

    private var lastCheckedButton: View? = null

    private val GALLERY = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCreatepostBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.viewBackgroundColorBlue.setOnClickListener {
            toggleCheckVisibility(it)
            viewBinding.viewContentBackground.setBackgroundColor(Color.parseColor("#D3F5FF"))
        }

        viewBinding.viewBackgroundColorGreen.setOnClickListener {
            toggleCheckVisibility(it)
            viewBinding.viewContentBackground.setBackgroundColor(Color.parseColor("#E4F9EB"))
        }

        viewBinding.viewBackgroundColorOrange.setOnClickListener {
            toggleCheckVisibility(it)
            viewBinding.viewContentBackground.setBackgroundColor(Color.parseColor("#FFDCCE"))
        }

        viewBinding.viewBackgroundColorYellow.setOnClickListener {
            toggleCheckVisibility(it)
            viewBinding.viewContentBackground.setBackgroundColor(Color.parseColor("#FCF9D0"))
        }

        viewBinding.viewBackgroundColorPink.setOnClickListener {
            toggleCheckVisibility(it)
            viewBinding.viewContentBackground.setBackgroundColor(Color.parseColor("#FBE0E4"))
        }

        viewBinding.imgCategoryQuestion.setOnClickListener {
            viewBinding.imgCategoryQuestion.isSelected = !viewBinding.imgCategoryQuestion.isSelected
            viewBinding.imgCategoryWorry.isSelected = false
        }

        viewBinding.imgCategoryWorry.setOnClickListener {
            viewBinding.imgCategoryWorry.isSelected = !viewBinding.imgCategoryWorry.isSelected
            viewBinding.imgCategoryQuestion.isSelected = false
        }

        if (checkPermission()) {
            showPhotos()
        } else {
            requestPermission()
        }
        clickCancelBtn()
    }

    private fun registerPost() {

//        viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
//

        viewBinding.btnSnsPostingRegister.setOnClickListener {
            val date = "3월 8일"
            val image = selectedPhoto.url
            val content = viewBinding.editContent.text.toString()
            val backgroundColor = viewBinding.viewContentBackground.background
            val contentBackgroundColor = (backgroundColor as? ColorDrawable)?.color ?: Color.WHITE

            val intent = Intent()
            intent.putExtra("date", date)
            intent.putExtra("image", image)
            intent.putExtra("content", content)
            intent.putExtra("backgroundColor", contentBackgroundColor)

            // Set the result and finish the activity
            setResult(RESULT_OK, intent)
            finish()
        }
    }


//        // 데이터 생성 및 ViewModel에 저장
//        val posting = Posting(date, R.drawable.style_around_image, content, contentBackgroundColor)
//
//        viewModel.addPosting(posting)
//
//        viewModel.postings.observe(this) { updatedPostings ->
//            for (posting in updatedPostings) {
//                Log.d("CreatePostActivity", "Posting Date: ${posting.date}")
//                Log.d("CreatePostActivity", "Posting Image URL: ${posting.image}")
//                Log.d("CreatePostActivity", "Posting Content: ${posting.content}")
//                Log.d(
//                    "CreatePostActivity",
//                    "Posting Background Color: ${posting.contentBackgroundColor}"
//                )
//            }
//
//            finish()
//            // postings LiveData가 업데이트되면 액티비티를 종료하고 SnsActivity를 시작
//            val intent = Intent(this, SnsActivity::class.java)
//            startActivity(intent)
//            finish()
//

    private fun toggleCheckVisibility(clickedView: View) {
        val clickedCheckId = colorCheckIds[clickedView.id] ?: return

        if (lastCheckedButton != null && lastCheckedButton != clickedView) {
            val lastCheckedCheckId = colorCheckIds[lastCheckedButton!!.id] ?: return
            viewBinding.root.findViewById<View>(lastCheckedCheckId).visibility = View.GONE
        }

        val clickedCheckView = viewBinding.root.findViewById<View>(clickedCheckId)
        clickedCheckView.visibility =
            if (clickedCheckView.visibility == View.VISIBLE) View.GONE else View.VISIBLE

        lastCheckedButton =
            if (clickedCheckView.visibility == View.VISIBLE) clickedView else null
    }

    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE
        )
    }

    private lateinit var selectedPhoto: Photo
    private fun showPhotos() {
        val photosList = getPhotosList()
        photosAdapter = PhotosAdapter(photosList,
            onPhotoClickListener = { clickedPhoto ->
                selectedPhoto = clickedPhoto
                registerPost()
            },
            onIconClickListener = {
                Toast.makeText(this, "New Icon Clicked!", Toast.LENGTH_SHORT).show()
            }
        )
        val recyclerView: RecyclerView = viewBinding.recyclerViewGallery
        recyclerView.layoutManager = GridLayoutManager(this, 5)
        recyclerView.adapter = photosAdapter
    }

    private fun getPhotosList(): List<Photo> {
        val photosList = mutableListOf<Photo>()

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA
        )

        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )

        cursor?.use {
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val data = cursor.getString(dataColumn)
                photosList.add(Photo(id, data))
            }
        }

        return photosList
    }

    private fun clickCancelBtn() {
        viewBinding.btnSnsPostingCancel.setOnClickListener {
            val intent = Intent(this, SnsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun clickRegisterBtn() {
        viewBinding.btnSnsPostingRegister.setOnClickListener {
            val intent = Intent(this, SnsActivity::class.java)
            startActivityForResult(intent, 100) // Use any re
        }
    }
}