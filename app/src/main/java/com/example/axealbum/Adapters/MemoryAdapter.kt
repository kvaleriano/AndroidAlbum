package com.example.axealbum.Adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.axealbum.Data.Memory
import com.example.axealbum.Helper.inflate
import com.example.axealbum.Helper.setAppFont
import com.example.axealbum.Helper.setAppFontBold
import com.example.axealbum.R

class MemoryAdapter(private val memories: List<Memory>): RecyclerView.Adapter<MemoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.row_memory, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount() = memories.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindMemory(memories[position])
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {

        private val memoryImageView: ImageView
        private val titleTextView: TextView
        private val commentTextView: TextView
        private var memory: Memory? = null

        init {
            memoryImageView = v.findViewById(R.id.memoryImageView)
            titleTextView = v.findViewById(R.id.titleTextView)
            commentTextView = v.findViewById(R.id.commentTextView)
            titleTextView.setAppFontBold()
            commentTextView.setAppFont()
        }

        fun bindMemory(memory: Memory) {

            /*val bitmapImage = BitmapFactory.decodeResource(itemView.resources, memory.image)
            val nh = bitmapImage.height * 512 / bitmapImage.width
            val scaled = Bitmap.createScaledBitmap(bitmapImage, 512, nh, true)*/
            this.memory = memory
            //memoryImageView.setImageBitmap(scaled)
            memoryImageView.setImageResource(memory.image)
            titleTextView.text = memory.title
            commentTextView.text = memory.comment
        }
    }
}