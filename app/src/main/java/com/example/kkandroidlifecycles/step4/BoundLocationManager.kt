/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.kkandroidlifecycles.step4

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.util.*

object BoundLocationManager {
    @JvmStatic
    fun bindLocationListenerIn(
        lifecycleOwner: LifecycleOwner?,
        listener: LocationListener, context: Context
    ) {
        BoundLocationListener(lifecycleOwner, listener, context)
    }

    internal class BoundLocationListener     //TODO: Add lifecycle observer
        (
        lifecycleOwner: LifecycleOwner?,
        private val mListener: LocationListener,
        private val mContext: Context
    ) : LifecycleObserver {
        private var mLocationManager: LocationManager? = null

        //TODO: Call this on resume
        @SuppressLint("MissingPermission")
        fun addLocationListener() {
            // Note: Use the Fused Location Provider from Google Play Services instead.
            // https://developers.google.com/android/reference/com/google/android/gms/location/FusedLocationProviderApi
            mLocationManager =
                mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            mLocationManager!!.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0,
                0f,
                mListener
            )
            Log.d("BoundLocationMgr", "Listener added")

            // Force an update with the last location, if available.
            val lastLocation = mLocationManager!!.getLastKnownLocation(
                LocationManager.GPS_PROVIDER
            )
            if (lastLocation != null) {
                mListener.onLocationChanged(lastLocation)
            }
        }

        //TODO: Call this on pause
        fun removeLocationListener() {
            if (mLocationManager == null) {
                return
            }
            mLocationManager!!.removeUpdates(mListener)
            mLocationManager = null
            Log.d("BoundLocationMgr", "Listener removed")
        }
    }
}