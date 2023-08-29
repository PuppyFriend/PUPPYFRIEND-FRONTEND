package com.example.puppyfriend_frontend.View.Sns

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyfriend_frontend.R
import com.example.puppyfriend_frontend.View.Sns.adapter.PhotosAdapter
import com.example.puppyfriend_frontend.View.Sns.model.Photo
import com.example.puppyfriend_frontend.databinding.FragmentCreastePostBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CreatePostFragment : Fragment() {
    private lateinit var viewBinding: FragmentCreastePostBinding

    private lateinit var photosAdapter: PhotosAdapter
    companion object {
        private const val READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 1
        private const val CAMERA_PERMISSION_REQUEST_CODE = 2
        private const val CAMERA_REQUEST_CODE =3
    }

    private val colorCheckIds = mapOf(
        R.id.view_background_color_blue to R.id.view_background_color_blue_check,
        R.id.view_background_color_green to R.id.view_background_color_green_check,
        R.id.view_background_color_orange to R.id.view_background_color_orange_check,
        R.id.view_background_color_yellow to R.id.view_background_color_yellow_check,
        R.id.view_background_color_pink to R.id.view_background_color_pink_check
    )

    private var lastCheckedButton: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewBinding = FragmentCreastePostBinding.inflate(layoutInflater)

        setupBackgroundColorListeners()

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
        clickRegisterBtn()

        return viewBinding.root
    }

    private fun setupBackgroundColorListeners() {
        val colorClickListeners = mapOf(
            R.id.view_background_color_blue to "#D3F5FF",
            R.id.view_background_color_green to "#E4F9EB",
            R.id.view_background_color_orange to "#FFDCCE",
            R.id.view_background_color_yellow to "#FCF9D0",
            R.id.view_background_color_pink to "#FBE0E4"
        )

        for ((colorViewId, colorHex) in colorClickListeners) {
            viewBinding.root.findViewById<View>(colorViewId).setOnClickListener {
                toggleCheckVisibility(it)
                viewBinding.viewContentBackground.setBackgroundColor(Color.parseColor(colorHex))
            }
        }
    }

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
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE
        )
    }

    private fun showPhotos() {
        val photosList = getPhotosList()

        photosAdapter = PhotosAdapter(photosList,
            onIconClickListener = {
                openCameraForResult()
                // 이미지 클릭 시 tv_text 내용 변경
            },
            onPhotoClickListener = {
                viewBinding.textImageInPost.text = "게시물 내 이미지 1/1"
            }

        )
        val recyclerView: RecyclerView = viewBinding.recyclerviewGallery
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 5)
        recyclerView.adapter = photosAdapter


    }

    private fun getPhotosList(): MutableList<Photo> {
        val photosList = mutableListOf<Photo>()

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA
        )

        val cursor = requireContext().contentResolver.query(
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
                val photo = Photo(id, data)
                photosList.add(photo)
                Log.d("PhotoInfo", "Image URL: ${photo.url}")
            }
        }

        return photosList
    }

    private fun clickCancelBtn() {
        viewBinding.btnSnsPostingCancel.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun clickRegisterBtn() {
        viewBinding.btnSnsPostingRegister.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun checkCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_REQUEST_CODE
        )
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera()
                } else {
                    Toast.makeText(requireContext(), "Camera permission denied.", Toast.LENGTH_SHORT).show()
                }
            }
            // ...
        }
    }

    private fun saveImageToGallery(imageUri: Uri) {
        MediaScannerConnection.scanFile(
            requireContext(),
            arrayOf(imageUri.path),
            null
        ) { _, _ ->
            Log.d("SaveImageToGallery", "Gallery updated")
            showPhotos()
        }

        Toast.makeText(requireContext(), "Image saved to gallery.", Toast.LENGTH_SHORT).show()
    }

    private fun openCameraForResult() {
        if (checkCameraPermission()) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        }else {
            requestCameraPermission()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            val imageUri = data?.data
            if (imageUri != null) {
                saveImageToGallery(imageUri)
                showPhotos() // 갤러리 업데이트
            }
        }
    }

}