package com.example.axealbum.Adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.axealbum.Data.Year
import com.example.axealbum.Helper.inflate
import com.example.axealbum.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.row_year.view.*

class YearAdapter(private val years: List<Year>): RecyclerView.Adapter<YearAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YearAdapter.ViewHolder {
        val inflatedView = parent.inflate(R.layout.row_year, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount() = years.size

    override fun onBindViewHolder(viewHolder: YearAdapter.ViewHolder, position: Int) {
        viewHolder.bindYear(years[position])
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener {

        private val yearImageView: CircleImageView
        private val yearTextView: TextView

        init {
            v.setOnClickListener(this)
            yearImageView = v.findViewById(R.id.yearImageView)
            yearTextView = v.findViewById(R.id.yearTextView)
        }

        override fun onClick(view: View?) {
            val context = itemView.context
            //val showYearIntent = Intent(context )
        }

        fun bindYear(year: Year) {
            yearTextView.text = year.name
            yearImageView.setImageResource(year.image)
        }
    }

}