package com.example.axealbum.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.axealbum.Data.Memory
import com.example.axealbum.R

class LandmarkActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landmark)

        intent.extras?.let {
            val year = it.getString("year", "2018")
            val landmark = it.getString("landmark", "Marzo")
        }
    }
}