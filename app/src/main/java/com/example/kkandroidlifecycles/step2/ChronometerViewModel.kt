package com.example.kkandroidlifecycles.step2

import android.util.Log
import androidx.lifecycle.ViewModel

class ChronometerViewModel: ViewModel() {
    private var mStartTime: Long? = null

    init {
        Log.i("ChronometerViewModel", "ChronometerViewModel created!")
    }

    fun getStartTime(): Long? {
        return mStartTime
    }

    fun setStartTime(startTime: Long) {
        mStartTime = startTime
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ChronometerViewModel", "ChronometerViewModel destroyed!")
    }
}