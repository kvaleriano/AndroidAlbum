package com.example.axealbum.Activities

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.example.axealbum.Adapters.LandmarkAdapter
import com.example.axealbum.Dao.DataBase
import com.example.axealbum.Dao.DbWorkerThread
import com.example.axealbum.R
import kotlinx.android.synthetic.main.activity_year.*

class YearActivity: AppCompatActivity() {

    private var mDb : DataBase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_year)

        mDb = DataBase.getInstance(this)

        val layoutManager = GridLayoutManager(this, 3)
        memoriesRecyclerView.layoutManager = layoutManager

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
                    memoriesRecyclerView.adapter = adapter
                }
            }
        }
    }
}