package com.example.sunginhong.sideproject_02

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.ChangeBounds
import android.util.Log
import android.view.animation.DecelerateInterpolator

class Main_DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(com.example.sunginhong.sideproject_02.R.layout.activity_card_detail)
        val bounds = ChangeBounds()
        bounds.duration = 300
        bounds.interpolator = DecelerateInterpolator(1.5f)
        window.sharedElementEnterTransition = bounds
        Log.d("ssss", "ssssss122112")
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onPause() {
        super.onPause()
    }

}