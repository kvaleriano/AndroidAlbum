package com.example.axealbum.Activities

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.example.axealbum.Adapters.LandmarkAdapter
import com.example.axealbum.Dao.DataBase
import com.example.axealbum.R
import kotlinx.android.synthetic.main.activity_year.*

class YearActivity: AppCompatActivity() {

    private var mDb : DataBase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_year)

        backImageView.setOnClickListener {
            onBackPressed()
        }

        mDb = DataBase.getInstance(this)

        val layoutManager = GridLayoutManager(this, 3)
        landmarksRecyclerView.layoutManager = layoutManager

        intent.extras?.let {
            val year = it.getString("year", "2018")
            fetchLandmarks(year)
        }
    }

    private fun fetchLandmarks(year: String) {
        AsyncTask.execute{
            val landmarks = mDb?.landmarkDao()?.getAllByYear(year)
            landmarks?.let {
                runOnUiThread {
                    val adapter = LandmarkAdapter(landmarks)
                    landmarksRecyclerView.adapter = adapter
                }
            }
        }
    }
}