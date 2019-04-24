package com.example.axealbum.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.example.axealbum.Adapters.YearAdapter
import com.example.axealbum.Dao.DataBase
import com.example.axealbum.Dao.DbWorkerThread
import com.example.axealbum.Data.Landmark
import com.example.axealbum.Data.Memory
import com.example.axealbum.Data.Year
import com.example.axealbum.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mDb : DataBase? = null
    private lateinit var mDbWorkerThread: DbWorkerThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()

        mDb = DataBase.getInstance(this)

        deleteAll()

        val years = ArrayList<Year>()
        val landmarks = ArrayList<Landmark>()
        val memories = ArrayList<Memory>()

        years.add(Year("2018", 1))
        years.add(Year("2019", 1))

        landmarks.add(getLandmark("Junio", 1, "2018"))
        landmarks.add(getLandmark("Julio", 1, "2018"))
        landmarks.add(getLandmark("Agosto", 1, "2018"))
        landmarks.add(getLandmark("Septiembre", 1, "2018"))
        landmarks.add(getLandmark("Octubre", 1, "2018"))
        landmarks.add(getLandmark("Noviembre", 1, "2018"))
        landmarks.add(getLandmark("Diciembre", 1, "2018"))
        landmarks.add(getLandmark("Enero", 1, "2019"))
        landmarks.add(getLandmark("Febrero", 1, "2019"))
        landmarks.add(getLandmark("Marzo", 1, "2019"))
        landmarks.add(getLandmark("Abril", 1, "2019"))
        landmarks.add(getLandmark("Mayo", 1, "2019"))

        insertLandmarksInDb(landmarks)
        insertMemoriesInDb(memories)

        val adapter = YearAdapter(years)
        yearsRecyclerView.adapter = adapter

        val layoutManager = GridLayoutManager(this, 3)
        yearsRecyclerView.layoutManager = layoutManager
    }

    private fun getLandmark(title: String, image: Int, year: String): Landmark {
        val landmark = Landmark()
        landmark.title = title
        landmark.image = image
        landmark.year = year
        return landmark
    }

    private fun getMemory(title: String, image: Int, comment: String, landmark: String, year: String): Memory {
        val memory = Memory()
        memory.title = title
        memory.image = image
        memory.comment = comment
        memory.landmark = landmark
        memory.year = year
        return memory
    }

    private fun deleteAll() {
        val task = Runnable {
            mDb?.landmarkDao()?.deleteAll()
            mDb?.memoryDao()?.deleteAll()
        }
        mDbWorkerThread.postTask(task)
    }

    private fun insertLandmarksInDb(landmarks: List<Landmark>) {
        val task = Runnable {
            val currentLandmarks = mDb?.landmarkDao()?.getAll()
            currentLandmarks?.let {
                if (it.isNotEmpty()) {
                    return@Runnable
                }
            }
            for (i in 0 until landmarks.size) {
                mDb?.landmarkDao()?.insert(landmarks[i])
            }
        }
        mDbWorkerThread.postTask(task)
    }

    private fun insertMemoriesInDb(memories: List<Memory>) {
        val task = Runnable {
            val currentMemories = mDb?.memoryDao()?.getAll()
            currentMemories?.let {
                if (it.isNotEmpty()) {
                    return@Runnable
                }
            }
            for (i in 0 until memories.size) {
                mDb?.memoryDao()?.insert(memories[i])
            }
        }
        mDbWorkerThread.postTask(task)
    }
}
