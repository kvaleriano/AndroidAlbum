package com.example.axealbum.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.example.axealbum.Adapters.LandmarkAdapter
import com.example.axealbum.Dao.DataBase
import com.example.axealbum.Dao.DbWorkerThread
import com.example.axealbum.Data.Memory
import com.example.axealbum.R
import kotlinx.android.synthetic.main.activity_main.*

class YearActivity: AppCompatActivity() {

    private var mDb : DataBase? = null
    private lateinit var mDbWorkerThread: DbWorkerThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_year)

        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()

        mDb = DataBase.getInstance(this)

        val layoutManager = GridLayoutManager(this, 3)
        yearsRecyclerView.layoutManager = layoutManager

        intent.extras?.let {
            val year = it.getString("year", "2018")
            fetchLandmarks(year)
        }
    }

    private fun fetchLandmarks(year: String) {
        val task = Runnable {
            val landmarks = mDb?.landmarkDao()?.getAllByYear(year)
            landmarks?.let {
                val adapter = LandmarkAdapter(landmarks)
                yearsRecyclerView.adapter = adapter

            }
        }
        mDbWorkerThread.postTask(task)
    }
}