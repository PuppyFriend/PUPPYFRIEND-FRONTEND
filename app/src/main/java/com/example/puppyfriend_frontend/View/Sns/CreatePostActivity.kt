package com.example.puppyfriend_frontend.View.Sns

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.View.Sns.adapter.PhotosAdapter
import com.example.puppyfriend_frontend.View.Sns.model.Photo
import com.example.puppyfriend_frontend.databinding.ActivityCreatepostBinding



class CreatePostActivity:AppCompatActivity() {

    private lateinit var viewBinding: ActivityCreatepostBinding
    private lateinit var photosAdapter: PhotosAdapter

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

//    var PICK_IMAGE_FROM_ALBUM = 0
//    var storage: FirebaseStorage? = null
//    var photoUri: Uri? = null

    private val GALLERY = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCreatepostBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnSnsPostingRegister.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent, GALLERY)
        }

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
        
        // 카테고리_질문 클릭 시 색변화
        viewBinding.imgCategoryQuestion.setOnClickListener{
            viewBinding.imgCategoryQuestion.isSelected = !viewBinding.imgCategoryQuestion.isSelected
            viewBinding.imgCategoryWorry.isSelected = false
        }

        // 카테고리_고민 클릭 시 색변화
        viewBinding.imgCategoryWorry.setOnClickListener{
            viewBinding.imgCategoryWorry.isSelected = !viewBinding.imgCategoryWorry.isSelected
            viewBinding.imgCategoryQuestion.isSelected = false
        }

        if (checkPermission()) {
            showPhotos()
        } else {
            requestPermission()
        }
    }

    // 다른 버튼을 클릭 시 기존 체크 이미지 삭제하는 함수
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

    // 갤러리 연동 및 권한 허용
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

    private fun showPhotos() {
        val photosList = getPhotosList()
        photosAdapter = PhotosAdapter(photosList)
        val recyclerView: RecyclerView = viewBinding.recyclerViewPhotos
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

        //    private fun openGallery() {
//        storage = FirebaseStorage.getInstance()
//
//        var photoPickerIntent = Intent(Intent.ACTION_PICK)
//        photoPickerIntent.type="image/*"
//        startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM)
//
//        viewBinding.btnSnsPostingRegister.setOnClickListener {
//            contentUpload()
//        }
//    }
//
//        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == RESULT_OK)
//            if (resultCode == GALLERY) {
//                var ImageData: Uri? = data?.data
//                Toast.makeText(this, ImageData.toString(), Toast.LENGTH_SHORT).show()
//                try {
//                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, ImageData)
//                    viewBinding.imgSnsPosting.setImageBitmap(bitmap)
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
        }
//    }


//
//    private fun contentUpload() {
//        var timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
//        var imageFileName = "IMAGE_" + timestamp + "_.png"
//
//        var storageRef = storage?.reference?.child("images")?.child(imageFileName)
//
//        storageRef?.putFile(photoUri!!)?.addOnSuccessListener {
//            val uploadStatusMessage = "Upload Status: Success"
//            Toast.makeText(this, uploadStatusMessage, Toast.LENGTH_LONG).show()
//
//            val intent = Intent(this, AddPhotoActivity::class.java)
//            intent.putExtra("upload_status", uploadStatusMessage)
//            startActivity(intent)
//        }?.addOnFailureListener{
//            val uploadStatusMessage = "Upload Status: Failed"
//            Toast.makeText(this, uploadStatusMessage, Toast.LENGTH_LONG).show()
//
//            val intent = Intent(this, AddPhotoActivity::class.java)
//            intent.putExtra("upload_status", uploadStatusMessage)
//            startActivity(intent)
//        }
//    }
