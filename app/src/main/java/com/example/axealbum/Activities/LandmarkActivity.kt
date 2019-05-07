package com.example.axealbum.Activities

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.axealbum.Adapters.MemoryAdapter
import com.example.axealbum.Dao.DataBase
import com.example.axealbum.R
import kotlinx.android.synthetic.main.activity_landmark.*

class LandmarkActivity: AppCompatActivity() {

    private var mDb : DataBase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landmark)

        backImageView.setOnClickListener {
            onBackPressed()
        }

        mDb = DataBase.getInstance(this)

        intent.extras?.let {
            val year = it.getString("year", "2018")
            val landmark = it.getString("landmark", "Marzo")
            fetchMemories(year, landmark)
        }
    }

    private fun fetchMemories(year: String, landmark: String) {
        AsyncTask.execute{
            val memories = mDb?.memoryDao()?.getAllByYearAndLandmark(year, landmark)
            memories?.let {
                runOnUiThread {
                    val adapter = MemoryAdapter(memories)
                    memoriesRecyclerView.adapter = adapter
                }
            }
        }
    }
}