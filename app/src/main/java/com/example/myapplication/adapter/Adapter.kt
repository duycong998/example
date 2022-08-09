package com.example.myapplication.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class Adapter(
    var list: MutableList<a>) : RecyclerView.Adapter<Adapter.NoteOldViewHolder>() {
    override fun onBindViewHolder(holder: NoteOldViewHolder, position: Int) {
        list?.get(position)?.let { holder.bindData(it) }
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteOldViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NoteOldViewHolder(inflater.inflate(R.layout.item, parent, false))
    }

    fun addNote(note: a) {
        if (!list.contains(note)) {
            list.add(note)
            notifyItemInserted(list.size)
        }
    }

    class NoteOldViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.image_view)

        fun bindData(note: a) {
            title.text = note.img
        }
    }
}