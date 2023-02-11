package com.application.notsapplication

import androidx.lifecycle.MutableLiveData

class NoteRepository(private val noteDao:NoteDao) {

    val allNotes:MutableLiveData<List<Note>> = noteDao.getAll()

    suspend fun insert(note:Note){
        noteDao.insert(note)
    }

    suspend fun delete(note:Note){
        noteDao.delete(note)
    }
}