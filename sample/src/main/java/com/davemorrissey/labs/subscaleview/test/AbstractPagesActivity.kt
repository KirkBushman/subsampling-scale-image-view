package com.davemorrissey.labs.subscaleview.test

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

abstract class AbstractPagesActivity(

        private val title: Int,
        private val layout: Int,

        private val notes: List<Page>

) : AppCompatActivity() {

    companion object {
        private const val BUNDLE_PAGE = "page"
    }

    protected var page: Int = 0
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        actionBar?.let {
            it.title = getString(title)
            it.setDisplayHomeAsUpEnabled(true)
        }

        findViewById<View>(R.id.next).setOnClickListener { next() }
        findViewById<View>(R.id.previous).setOnClickListener { previous() }
        if (savedInstanceState != null && savedInstanceState.containsKey(BUNDLE_PAGE)) {
            page = savedInstanceState.getInt(BUNDLE_PAGE)
        }
    }

    override fun onResume() {
        super.onResume()
        updateNotes()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(BUNDLE_PAGE, page)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    private operator fun next() {
        page++
        updateNotes()
    }

    private fun previous() {
        page--
        updateNotes()
    }

    private fun updateNotes() {
        if (page > notes.size - 1) {
            return
        }
        actionBar?.setSubtitle(notes[page].subtitle)
        (findViewById<View>(R.id.note) as TextView).setText(notes[page].text)
        findViewById<View>(R.id.next).visibility = if (page >= notes.size - 1) View.INVISIBLE else View.VISIBLE
        findViewById<View>(R.id.previous).visibility = if (page <= 0) View.INVISIBLE else View.VISIBLE
        onPageChanged(page)
    }

    protected open fun onPageChanged(page: Int) {}
}
