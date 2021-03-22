package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private val context: Context, private val onClickItem: OnClickItem) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    val allNotes = ArrayList<Notes>()

    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.text)
        val button = itemView.findViewById<ImageView>(R.id.ivDeleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )
        view.button.setOnClickListener {
            onClickItem.onItemClick(allNotes[view.adapterPosition])
        }
        return view
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.textView.text = currentNote.text

    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Notes>) {
        allNotes.clear()
        allNotes.addAll(newList);

        notifyDataSetChanged()
    }
}

interface OnClickItem {
    fun onItemClick(notes: Notes)

}