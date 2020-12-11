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
package com.example.kkandroidlifecycles.step6

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kkandroidlifecycles.R

/**
 * Shows a simple form with a button and displays the value of a property in a ViewModel.
 */
class SavedStateActivity : AppCompatActivity() {
    lateinit var mSavedStateViewModel: SavedStateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.saved_state_activity)

        // Obtain the ViewModel
        mSavedStateViewModel = ViewModelProvider(this).get(SavedStateViewModel::class.java)

        // Show the ViewModel property's value in a TextView
        mSavedStateViewModel.getName()?.observe(
            this,
            { savedString ->
                (findViewById<View>(R.id.saved_vm_tv) as TextView).text =
                    getString(R.string.saved_in_vm, savedString)
            })

        // Save button
        findViewById<View>(R.id.save_bt).setOnClickListener {
            val newName = (findViewById<View>(R.id.name_et) as EditText).text.toString()
            mSavedStateViewModel.saveNewName(newName)
        }
    }
}