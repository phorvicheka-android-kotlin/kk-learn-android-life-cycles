package com.example.kkandroidlifecycles

import android.os.Bundle
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity

class ChronoActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chronometer = findViewById<Chronometer>(R.id.chronometer)
        chronometer.start()
    }
}