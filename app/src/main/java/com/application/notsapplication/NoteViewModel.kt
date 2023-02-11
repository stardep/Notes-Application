package com.application.notsapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes: MutableLiveData<List<Note>>
    private val noteRepository:NoteRepository
    init{
        val noteDao = NoteDatabase.getDatabase(application).getNoteDao()
         noteRepository = NoteRepository(noteDao)
        allNotes = noteRepository.allNotes
    }

    fun deleteNote(note:Note) = viewModelScope.launch(Dispatchers.IO)
    {
    noteRepository.delete(note)
    }

    fun insetNote(note:Note) = viewModelScope.launch (Dispatchers.IO){
        noteRepository.insert(note)
    }
}