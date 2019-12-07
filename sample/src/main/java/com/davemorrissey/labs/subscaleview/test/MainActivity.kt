package com.davemorrissey.labs.subscaleview.test

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.davemorrissey.labs.subscaleview.test.animation.AnimationActivity
import com.davemorrissey.labs.subscaleview.test.basicfeatures.BasicFeaturesActivity
import com.davemorrissey.labs.subscaleview.test.configuration.ConfigurationActivity
import com.davemorrissey.labs.subscaleview.test.eventhandling.EventHandlingActivity
import com.davemorrissey.labs.subscaleview.test.eventhandlingadvanced.AdvancedEventHandlingActivity
import com.davemorrissey.labs.subscaleview.test.extension.ExtensionActivity
import com.davemorrissey.labs.subscaleview.test.imagedisplay.ImageDisplayActivity
import com.davemorrissey.labs.subscaleview.test.scaletype.ScaleTypeActivity
import com.davemorrissey.labs.subscaleview.test.viewpager.ViewPagerActivity
import kotlinx.android.synthetic.main.main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setTitle(R.string.main_title)
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        basicFeatures.setOnClickListener { startActivity(BasicFeaturesActivity::class.java) }
        imageDisplay.setOnClickListener { startActivity(ImageDisplayActivity::class.java) }
        eventHandling.setOnClickListener { startActivity(EventHandlingActivity::class.java) }
        advancedEventHandling.setOnClickListener { startActivity(AdvancedEventHandlingActivity::class.java) }
        viewPagerGalleries.setOnClickListener { startActivity(ViewPagerActivity::class.java) }
        animation.setOnClickListener { startActivity(AnimationActivity::class.java) }
        extension.setOnClickListener { startActivity(ExtensionActivity::class.java) }
        configuration.setOnClickListener { startActivity(ConfigurationActivity::class.java) }
        scaletype.setOnClickListener { startActivity(ScaleTypeActivity::class.java) }
        github.setOnClickListener { openGitHub() }
    }

    private fun startActivity(activity: Class<out Activity>) {
        startActivity(Intent(this, activity))
    }

    private fun openGitHub() {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse("https://github.com/davemorrissey/subsampling-scale-image-view")
        startActivity(i)
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}
