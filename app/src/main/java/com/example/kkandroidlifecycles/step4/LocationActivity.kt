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
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.kkandroidlifecycles.R
import java.util.*

class LocationActivity : AppCompatActivity() {
    private val mGpsListener: LocationListener = MyLocationListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.location_activity)

        val ACCESS_FINE_LOCATION_PERMISSION = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        val ACCESS_COARSE_LOCATION_PERMISSION = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        val PERMISSION_LIST = ArrayList<String>()
        if (ACCESS_FINE_LOCATION_PERMISSION != PackageManager.PERMISSION_GRANTED) {
            PERMISSION_LIST.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (ACCESS_COARSE_LOCATION_PERMISSION != PackageManager.PERMISSION_GRANTED) {
            PERMISSION_LIST.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        if (PERMISSION_LIST.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this, PERMISSION_LIST.toTypedArray(),
                REQUEST_LOCATION_PERMISSION_CODE
            )
        } else {
            bindLocationListener()
        }

        /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                REQUEST_LOCATION_PERMISSION_CODE
            )
        } else {
            bindLocationListener()
        }*/
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        /*if (grantResults.size > 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            bindLocationListener()
        } else {
            Toast.makeText(this, "This sample requires Location access", Toast.LENGTH_LONG).show()
        }*/

        var isPermissionAccessFineLocationGranted = false
        var isPermissionAccessCoarseLocationGranted = false
        when (requestCode) {
            REQUEST_LOCATION_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty()) {
                    var i = 0
                    while (i < permissions.size) {
                        if (permissions[i] == Manifest.permission.ACCESS_FINE_LOCATION) {
                            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                                isPermissionAccessFineLocationGranted = true
                            }
                        } else if (permissions[i] == Manifest.permission.ACCESS_COARSE_LOCATION) {
                            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                                isPermissionAccessCoarseLocationGranted = true
                            }
                        }
                        i++
                    }
                }
                if (isPermissionAccessFineLocationGranted && isPermissionAccessCoarseLocationGranted) {
                    bindLocationListener()
                } else {
                    Toast.makeText(this, "This sample requires Location access", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

    }

    private fun bindLocationListener() {
        BoundLocationManager.bindLocationListenerIn(this, mGpsListener, getApplicationContext())
    }

    private inner class MyLocationListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            val textView: TextView = findViewById<TextView>(R.id.location)
            textView.setText(location.latitude.toString() + ", " + location.longitude)
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {
            Toast.makeText(
                this@LocationActivity,
                "Provider enabled: $provider", Toast.LENGTH_SHORT
            ).show()
        }

        override fun onProviderDisabled(provider: String) {}
    }

    companion object {
        private const val REQUEST_LOCATION_PERMISSION_CODE = 1
    }
}