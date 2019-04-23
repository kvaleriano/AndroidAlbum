package com.example.axealbum.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.example.axealbum.Adapters.YearAdapter
import com.example.axealbum.Data.Memory
import com.example.axealbum.Data.Year
import com.example.axealbum.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val years = ArrayList<Year>()

        var memories2018 = ArrayList<Memory>()
        var year2018 = Year("2018", 1, memories2018)

        var memories2019 = ArrayList<Memory>()
        var year2019 = Year("2019", 1, memories2019)

        years.add(year2018)
        years.add(year2019)

        val adapter = YearAdapter(years)
        yearsRecyclerView.adapter = adapter

        val layoutManager = GridLayoutManager(this, 3)
        yearsRecyclerView.layoutManager = layoutManager
    }
}
