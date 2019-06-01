package com.example.axealbum.Adapters

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.axealbum.Activities.YearActivity
import com.example.axealbum.Data.Year
import com.example.axealbum.Helper.inflate
import com.example.axealbum.Helper.setAppFont
import com.example.axealbum.Helper.setAppFontBold
import com.example.axealbum.R
import de.hdodenhof.circleimageview.CircleImageView

class YearAdapter(private val years: List<Year>): RecyclerView.Adapter<YearAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.row_year, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount() = years.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindYear(years[position])
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener {

        private val yearImageView: CircleImageView
        private val yearTextView: TextView
        private var year: Year? = null

        init {
            v.setOnClickListener(this)
            yearImageView = v.findViewById(R.id.yearImageView)
            yearTextView = v.findViewById(R.id.yearTextView)
            yearTextView.setAppFontBold()
        }

        override fun onClick(view: View?) {
            year?.let {
                val context = itemView.context
                val showYearIntent = Intent(context, YearActivity::class.java)
                val bundle = Bundle()
                bundle.putString("year", it.name)
                showYearIntent.putExtras(bundle)
                context.startActivity(showYearIntent)
            }
        }

        fun bindYear(year: Year) {
            yearTextView.text = year.name
            yearImageView.setImageResource(year.image)
            this.year = year
        }
    }

}