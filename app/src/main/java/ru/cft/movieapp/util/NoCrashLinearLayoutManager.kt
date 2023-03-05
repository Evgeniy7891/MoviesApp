package ru.cft.movieapp.util

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NoCrashLinearLayoutManager : LinearLayoutManager(MAIN) {

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (e: IndexOutOfBoundsException) {
            e.printStackTrace()
            Toast.makeText(
                MAIN,
                "An update is underway",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
