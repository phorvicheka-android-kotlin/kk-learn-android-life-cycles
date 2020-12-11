package com.example.kkandroidlifecycles.step3

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

/**
 * A ViewModel used for the [com.example.kkandroidlifecycles.step3.ChronoActivity3].
 */
class LiveDataTimerViewModel : ViewModel() {
    private val mElapsedTime = MutableLiveData<Long>()
    private val mInitialTime: Long = SystemClock.elapsedRealtime()
    private val timer: Timer = Timer()

    // Will be used when step is completed
    val elapsedTime: LiveData<Long>
        get() = mElapsedTime

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    companion object {
        private const val ONE_SECOND = 1000
    }

    init {
        // Update the elapsed time every second.
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000

                // setValue() cannot be called from a background thread so post to main thread.
                // post the new value with LiveData.postValue()
                mElapsedTime.postValue(newValue)

            }
        }, ONE_SECOND.toLong(), ONE_SECOND.toLong())
    }
}