package com.example.puppyfriend_frontend.View.Walk

import android.R
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyfriend_frontend.WalkFilterFragment
import com.example.puppyfriend_frontend.WalkNeighborFragment
import com.example.puppyfriend_frontend.databinding.ActivityWalkBinding
import net.daum.mf.map.api.MapView


class WalkActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityWalkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = ActivityWalkBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        val mapView = MapView(this)
        viewBinding.clKakaoMapView.addView(mapView)
        val fragmentManager=supportFragmentManager
        val filterFragment= WalkFilterFragment()
        val neighborFragment= WalkNeighborFragment()
        viewBinding.btnTestFilter.setOnClickListener {
            fragmentManager.beginTransaction().replace(viewBinding.frame.id, filterFragment!!).commit()
        }
        viewBinding.btnTestNeighbor.setOnClickListener {
            fragmentManager.beginTransaction().replace(viewBinding.frame.id, neighborFragment!!).commit()
        }

    }


}