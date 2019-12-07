package com.davemorrissey.labs.subscaleview.test.scaletype

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.davemorrissey.labs.subscaleview.test.R
import kotlinx.android.synthetic.main.scaletype_activity.*
import java.io.IOException


class ScaleTypeActivity : AppCompatActivity() {

    private val scaleTypeArray by lazy { resources.getStringArray(R.array.spinner_scaletype) }
    private val scaleTypeSsivArray by lazy { resources.getStringArray(R.array.spinner_scaletype_ssiv) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scaletype_activity)

        radio_imageview.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                showImageView()
            }
        }

        radio_ssiv.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                showSSIV()
            }
        }

        ArrayAdapter.createFromResource(this, R.array.spinner_scaletype,
                android.R.layout.simple_spinner_item).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinner_scaletype_imageview.adapter = adapter
        }

        spinner_scaletype_imageview.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val scaleTypeStr = scaleTypeArray[position]
                val scaleType = getScaleTypeFromString(scaleTypeStr)

                if (imageview.visibility == View.VISIBLE) {
                    imageview.scaleType = scaleType
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        ArrayAdapter.createFromResource(this, R.array.spinner_scaletype_ssiv,
                android.R.layout.simple_spinner_item).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinner_scaletype_ssiv.adapter = adapter
        }

        spinner_scaletype_ssiv.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val scaleTypeStr = scaleTypeSsivArray[position]
                val scaleType = getScaleTypeFromString2(scaleTypeStr)

                if (ssiv.visibility == View.VISIBLE) {

                    Log.i("SHIS KEBAB", "scaleType: $scaleTypeStr")

                    ssiv.setMinimumScaleType(scaleType)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        showImageView()
    }

    private fun showImageView() {

        imageview.visibility = View.VISIBLE
        ssiv.visibility = View.GONE

        val scaleTypeStr = scaleTypeArray[spinner_scaletype_imageview.selectedItemPosition]
        val scaleType = getScaleTypeFromString(scaleTypeStr)

        if (imageview.visibility == View.VISIBLE) {
            imageview.scaleType = scaleType
        }

        try {
            val ims = assets.open("sanmartino.jpg")
            val d = Drawable.createFromStream(ims, null)
            imageview.setImageDrawable(d)
        } catch (ex: IOException) {
            return
        }
    }

    private fun showSSIV() {

        imageview.visibility = View.GONE
        ssiv.visibility = View.VISIBLE

        val scaleTypeStr = scaleTypeSsivArray[spinner_scaletype_ssiv.selectedItemPosition]
        val scaleType = getScaleTypeFromString2(scaleTypeStr)

        if (ssiv.visibility == View.VISIBLE) {
            ssiv.setMinimumScaleType(scaleType)
        }

        ssiv.setImage(ImageSource.asset("sanmartino.jpg"))
    }

    private fun getScaleTypeFromString(raw: String): ImageView.ScaleType {

        return when(raw) {

            "CENTER" -> ImageView.ScaleType.CENTER
            "CENTER_CROP" -> ImageView.ScaleType.CENTER_CROP
            "CENTER_INSIDE" -> ImageView.ScaleType.CENTER_INSIDE
            "FIT_CENTER" -> ImageView.ScaleType.FIT_CENTER
            "FIT_END" -> ImageView.ScaleType.FIT_END
            "FIT_START" -> ImageView.ScaleType.FIT_START
            "FIT_XY" -> ImageView.ScaleType.FIT_XY
            "MATRIX" -> ImageView.ScaleType.MATRIX

            else -> ImageView.ScaleType.CENTER
        }
    }

    private fun getScaleTypeFromString2(raw: String): Int {

        return when(raw) {
            "SCALE_TYPE_CENTER_INSIDE" -> SubsamplingScaleImageView.SCALE_TYPE_CENTER_INSIDE
            "SCALE_TYPE_CENTER_CROP" -> SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP
            "SCALE_TYPE_CUSTOM" -> SubsamplingScaleImageView.SCALE_TYPE_CUSTOM
            "SCALE_TYPE_START" -> SubsamplingScaleImageView.SCALE_TYPE_START

            else -> SubsamplingScaleImageView.SCALE_TYPE_CENTER_INSIDE
        }
    }
}