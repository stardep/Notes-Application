package com.application.notsapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRecyclerViewAdapter(private val context: Context, private val listener:INOtesRVAdapter):RecyclerView.Adapter<NotesRecyclerViewAdapter.NoteViewHolder>() {

    val allNotes = ArrayList<Note>()
    inner class NoteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        val textView = itemView.findViewById<TextView>(R.id.text)
        val deleteButton = itemView.findViewById<Button>(R.id.deleteButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false))
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClick(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val currentNode = allNotes.get(position)
        holder.textView.text = currentNode.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList:List<Note>)
    {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

}

interface INOtesRVAdapter{
    fun onItemClick(note:Note)
}