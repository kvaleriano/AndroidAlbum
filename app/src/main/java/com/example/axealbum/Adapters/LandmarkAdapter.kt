package com.example.axealbum.Adapters

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.axealbum.Activities.LandmarkActivity
import com.example.axealbum.Data.Landmark
import com.example.axealbum.Helper.inflate
import com.example.axealbum.R
import de.hdodenhof.circleimageview.CircleImageView

class LandmarkAdapter(private val memories: List<Landmark>): RecyclerView.Adapter<LandmarkAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.row_landmark, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount() = memories.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindMemory(memories[position])
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener {

        private val memoryImageView: CircleImageView
        private val memoryTextView: TextView
        private var landmark: Landmark? = null

        init {
            v.setOnClickListener(this)
            memoryImageView = v.findViewById(R.id.memoryImageView)
            memoryTextView = v.findViewById(R.id.memoryTextView)
        }

        override fun onClick(view: View?) {
            landmark?.let {
                val context = itemView.context
                val showMemoryIntent = Intent(context, LandmarkActivity::class.java)
                val bundle = Bundle()
                bundle.putString("year", it.year)
                bundle.putString("landmark", it.title)
                showMemoryIntent.putExtras(bundle)
                context.startActivity(showMemoryIntent)
            }
        }

        fun bindMemory(landmark: Landmark) {
            this.landmark = landmark
            memoryImageView.setImageResource(landmark.image)
            memoryTextView.text = landmark.title
        }
    }

}