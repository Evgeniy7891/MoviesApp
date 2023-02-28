package ru.cft.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.cft.movieapp.R
import ru.cft.movieapp.util.MAIN

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MAIN = this
    }
}