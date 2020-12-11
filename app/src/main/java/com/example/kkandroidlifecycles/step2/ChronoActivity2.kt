package com.example.kkandroidlifecycles.step2

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kkandroidlifecycles.R

class ChronoActivity2 : AppCompatActivity() {

    private lateinit var viewModel: ChronometerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // The ViewModelStore provides a new ViewModel or one previously created.
        Log.i("ChronoActivity1", "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(
            ChronometerViewModel::class.java
        )

        // Get the chronometer reference
        val chronometer = findViewById<Chronometer>(R.id.chronometer)

        if (viewModel.getStartTime() == null) {
            // If the start date is not defined, it's a new ViewModel so set it.
            val startTime = SystemClock.elapsedRealtime()
            viewModel.setStartTime(startTime)
            chronometer.base = startTime
        } else {
            // Otherwise the ViewModel has been retained, set the chronometer's base to the original
            // starting time.
            chronometer.base = viewModel.getStartTime()!!
        }

        chronometer.start()
    }
}