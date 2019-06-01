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
import com.example.axealbum.Helper.setAppFontBold
import com.example.axealbum.R
import de.hdodenhof.circleimageview.CircleImageView

class LandmarkAdapter(private val landmarks: List<Landmark>): RecyclerView.Adapter<LandmarkAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.row_landmark, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount() = landmarks.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindLandmark(landmarks[position])
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener {

        private val landmarkImageView: CircleImageView
        private val landmarkTextView: TextView
        private var landmark: Landmark? = null

        init {
            v.setOnClickListener(this)
            landmarkImageView = v.findViewById(R.id.landmarkImageView)
            landmarkTextView = v.findViewById(R.id.landmarkTextView)
            landmarkTextView.setAppFontBold()
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

        fun bindLandmark(landmark: Landmark) {
            this.landmark = landmark
            landmarkImageView.setImageResource(landmark.image)
            landmarkTextView.text = landmark.title
        }
    }
}