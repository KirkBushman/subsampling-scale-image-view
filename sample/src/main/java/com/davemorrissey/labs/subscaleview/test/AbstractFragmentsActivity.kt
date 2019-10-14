package com.davemorrissey.labs.subscaleview.test

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

abstract class AbstractFragmentsActivity(

        @StringRes
        private val title: Int,
        @LayoutRes
        private val layout: Int,

        private val notes: List<Page>

) : AppCompatActivity() {

    companion object {

        private const val BUNDLE_PAGE = "page"
    }

    private var page: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        actionBar?.let {
            it.title = getString(title)
            it.setDisplayHomeAsUpEnabled(true)
        }

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

    operator fun next() {
        page++
        updateNotes()
    }

    fun previous() {
        page--
        updateNotes()
    }

    private fun updateNotes() {
        if (page > notes.size - 1) {
            return
        }
        actionBar?.setSubtitle(notes[page].subtitle)
        onPageChanged(page)
    }

    protected abstract fun onPageChanged(page: Int)
}
