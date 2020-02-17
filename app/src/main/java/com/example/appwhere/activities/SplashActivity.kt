package com.example.appwhere.activities

import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.example.appwhere.R
import com.example.appwhere.bases.BaseActivity
import kotlinx.android.synthetic.main.content_splash.*

class SplashActivity : BaseActivity() {

    var DURATION = 3000
    var TIMER_LATER = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var fadeIn = AlphaAnimation(0.0f, 1.0f)
        fadeIn.duration = DURATION.toLong()
        fadeIn.startOffset = TIMER_LATER.toLong()
        fadeIn.fillAfter = true

        var fadeOut = AlphaAnimation(1.0f, 0.0f)
        fadeOut.duration = DURATION.toLong()
        fadeOut.startOffset = TIMER_LATER.toLong()
        fadeOut.fillAfter = true

        fadeIn.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                imageSplash.visibility = View.VISIBLE
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                imageSplash.startAnimation(fadeOut)
            }
        })

        fadeOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                launchLoginActivity()
            }

            override fun onAnimationStart(p0: Animation?) {
            }

        })

        imageSplash.startAnimation(fadeIn)

    }
}
