package com.example.puppyfriend_frontend.View.SignUp

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.puppyfriend_frontend.Model.UserJoinDto
import com.example.puppyfriend_frontend.View.Login.LoginActivity
import com.example.puppyfriend_frontend.ViewModel.User.SignUp.SignUpResult
import com.example.puppyfriend_frontend.ViewModel.User.SignUp.SignUpService
import com.example.puppyfriend_frontend.databinding.ActivitySignupLocationBinding
import com.google.android.gms.location.LocationServices
import java.util.Locale
import kotlin.math.sign

class SignUpLocationActivity : AppCompatActivity(), SignUpResult {
    lateinit var viewBinding: ActivitySignupLocationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding =  ActivitySignupLocationBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val name = intent.getStringExtra("name")
        val nickname = intent.getStringExtra("nickname")
        val uid = intent.getStringExtra("uid")
        val password = intent.getStringExtra("password")
        val gender = intent.getBooleanExtra("gender", false)
        val birth = intent.getStringExtra("birth")
        val marketing = intent.getBooleanExtra("marketing", false)
        val email = intent.getStringExtra("email")

        Log.d("가져온 값 : ", "name : ${name}, nickname : ${nickname}, uid : ${uid}, password : ${password}, gender : ${gender}, birth : ${birth}, marketing : ${marketing}, email : ${email}")

        viewBinding.btnGetLocation.setOnClickListener {
            getLocation(viewBinding.textLocationGet)
        }

        viewBinding.btnDone.setOnClickListener {
            signUp(name!!, nickname!!, uid!!, password!!, gender, birth!!, marketing, email!!)
        }

        initActionBar()
    }

    fun initActionBar(){
        viewBinding.actionbar.appbarPageNameLeftTv.text = "위치정보"
        viewBinding.actionbar.appbarBackBtn.setOnClickListener { onBackPressed() }
    }

    override fun onStart() {
        super.onStart()
        RequestPermissionsUtil(this).requestLocation() // 위치 권한 요청
    }

    inner class RequestPermissionsUtil(private val context: Context) {
        private val REQUEST_LOCATION = 1

        /** 위치 권한 SDK 버전 29 이상**/
        @RequiresApi(Build.VERSION_CODES.Q)
        private val permissionsLocationUpApi29Impl = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION
        )

        /** 위치 권한 SDK 버전 29 이하**/
        @TargetApi(Build.VERSION_CODES.P)
        private val permissionsLocationDownApi29Impl = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        /** 위치정보 권한 요청**/
        fun requestLocation() {
            if (Build.VERSION.SDK_INT >= 29) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        permissionsLocationUpApi29Impl[0]
                    ) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(
                        context,
                        permissionsLocationUpApi29Impl[1]
                    ) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(
                        context,
                        permissionsLocationUpApi29Impl[2]
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        context as Activity,
                        permissionsLocationUpApi29Impl,
                        REQUEST_LOCATION
                    )
                }
            } else {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        permissionsLocationDownApi29Impl[0]
                    ) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(
                        context,
                        permissionsLocationDownApi29Impl[1]
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        context as Activity,
                        permissionsLocationDownApi29Impl,
                        REQUEST_LOCATION
                    )
                }
            }
        }
    }
    @SuppressLint("MissingPermission")
    private fun getLocation(textView: TextView) {
        val fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this@SignUpLocationActivity)

        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { success: Location? ->
                success?.let { location ->
                    var currentLocation = "현재 위치"
                    var geocoder : Geocoder = Geocoder(this, Locale.KOREAN)
                    var locationProviderClient = LocationServices.getFusedLocationProviderClient(this)
                    var mResultList : List<Address>? = null

                    try{
                        locationProviderClient.lastLocation.addOnSuccessListener {
                            var latitude = it.latitude
                            var longitude = it.longitude
                            try{
                                mResultList = geocoder.getFromLocation(latitude, longitude, 1)
                            } catch (e : Exception){
                                e.printStackTrace()
                                Toast.makeText(this, "좌표를 변환하지 못했습니다.", Toast.LENGTH_SHORT).show()
                            }
                            if(mResultList != null){
                                Log.d("CheckCurrentLocation", mResultList!![0].getAddressLine(0))
                                currentLocation = mResultList!![0].getAddressLine(0)
                                currentLocation = currentLocation.substring(9)
                                textView.text = currentLocation
                            }
                        }
                    } catch (e : Exception){
                        e.printStackTrace()
                        Toast.makeText(this, "위치 정보를 받아오지 못했습니다.", Toast.LENGTH_SHORT).show()
                    }
//                    textView.text =
//                        "${location.latitude}, ${location.longitude}"
                }
            }
            .addOnFailureListener { fail ->
                textView.text = fail.localizedMessage
            }
    }

    private fun signUp(name: String, nickname : String, uid : String, password : String, gender: Boolean, birth : String, marketing : Boolean, email : String){
        val signUpService = SignUpService()
        signUpService.setSignUpResult(this)
        signUpService.signUp(UserJoinDto(birth, email, gender, viewBinding.textLocationGet.text.toString(), marketing, name, nickname, password, uid ))
    }

    override fun signUpSuccess(code: Int, message: String) {
        Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, LoginActivity::class.java)
        finishAffinity()
        startActivity(intent)
    }

    override fun signUpCreated(code: Int, message: String) {
        Toast.makeText(this, "회원가입에 실패하였습니다. $message", Toast.LENGTH_SHORT).show()
    }

    override fun signUpUnautorized(code: Int, message: String) {
        Toast.makeText(this, "회원가입에 실패하였습니다. $message", Toast.LENGTH_SHORT).show()
    }

    override fun signUpForbidden(code: Int, message: String) {
        Toast.makeText(this, "회원가입에 실패하였습니다. $message", Toast.LENGTH_SHORT).show()
    }

    override fun signUpFailure(code: Int, message: String) {
        Toast.makeText(this, "회원가입에 실패하였습니다. $message", Toast.LENGTH_SHORT).show()
    }
}