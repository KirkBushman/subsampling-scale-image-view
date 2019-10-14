package com.davemorrissey.labs.subscaleview.test.basicfeatures

import android.os.Bundle

import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.test.AbstractPagesActivity
import com.davemorrissey.labs.subscaleview.test.Page

import com.davemorrissey.labs.subscaleview.test.R.string.*
import com.davemorrissey.labs.subscaleview.test.R.layout.*
import kotlinx.android.synthetic.main.pages_activity.*

class BasicFeaturesActivity : AbstractPagesActivity(

        basic_title,
        pages_activity,

        listOf(
                Page(basic_p1_subtitle, basic_p1_text),
                Page(basic_p2_subtitle, basic_p2_text),
                Page(basic_p3_subtitle, basic_p3_text),
                Page(basic_p4_subtitle, basic_p4_text),
                Page(basic_p5_subtitle, basic_p5_text)
        )) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageView.setImage(ImageSource.asset("sanmartino.jpg"))
    }
}
