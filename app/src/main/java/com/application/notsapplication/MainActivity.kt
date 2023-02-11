package com.application.notsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), INOtesRVAdapter {
    lateinit var viewModel: NoteViewModel
    lateinit var recyclerView:RecyclerView
    lateinit var editText: EditText
    lateinit var recyclerViewAdapter:NotesRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = NotesRecyclerViewAdapter(this,this)
        editText = findViewById(R.id.input)
        recyclerView.adapter =recyclerViewAdapter
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this) {list->
            list?.let {
                recyclerViewAdapter.updateList(it)
            }


        }

    }

    override fun onItemClick(note: Note) {

        viewModel.deleteNote(note)
        Toast.makeText(this,"Deleted ${note.text}",Toast.LENGTH_LONG).show()
    }

    fun submitData(view: View) {
        val noteText = editText.text.toString()
        if(noteText.isNotEmpty())
        {
            viewModel.insetNote(Note(noteText))
        }
    }
}