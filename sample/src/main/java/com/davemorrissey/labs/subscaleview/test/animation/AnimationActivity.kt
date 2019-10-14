package com.davemorrissey.labs.subscaleview.test.animation

import android.graphics.PointF
import android.os.Bundle

import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.test.AbstractPagesActivity
import com.davemorrissey.labs.subscaleview.test.Page

import java.util.Random

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView.*
import com.davemorrissey.labs.subscaleview.test.R.string.*
import com.davemorrissey.labs.subscaleview.test.R.layout.*
import kotlinx.android.synthetic.main.animation_activity.*

class AnimationActivity : AbstractPagesActivity(

        animation_title,
        animation_activity,

        listOf(
                Page(animation_p1_subtitle, animation_p1_text),
                Page(animation_p2_subtitle, animation_p2_text),
                Page(animation_p3_subtitle, animation_p3_text),
                Page(animation_p4_subtitle, animation_p4_text)
        )) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        play.setOnClickListener { play() }
        imageView.setImage(ImageSource.asset("sanmartino.jpg"))
    }

    override fun onPageChanged(page: Int) {
        if (page == 2) {
            imageView.setPanLimit(PAN_LIMIT_CENTER)
        } else {
            imageView.setPanLimit(PAN_LIMIT_INSIDE)
        }
    }

    private fun play() {
        val random = Random()
        if (imageView.isReady) {
            val scale = random.nextFloat() * (imageView.maxScale - imageView.minScale) + imageView.minScale
            val center = PointF(random.nextInt(imageView.sWidth).toFloat(), random.nextInt(imageView.sHeight).toFloat())
            imageView.setPin(center)

            val animationBuilder = imageView.animateScaleAndCenter(scale, center)
            if (page == 3) {
                animationBuilder!!
                        .withDuration(2000)
                        .withEasing(EASE_OUT_QUAD)
                        .withInterruptible(false)
                        .start()
            } else {
                animationBuilder!!
                        .withDuration(750)
                        .start()
            }
        }
    }
}
